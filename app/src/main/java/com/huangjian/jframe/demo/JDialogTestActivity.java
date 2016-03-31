package com.huangjian.jframe.demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RadioGroup;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.huangjian.jframe.ui.view.JDialog;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.GridHolder;
import com.orhanobut.dialogplus.Holder;
import com.orhanobut.dialogplus.ListHolder;
import com.orhanobut.dialogplus.OnCancelListener;
import com.orhanobut.dialogplus.OnClickListener;
import com.orhanobut.dialogplus.OnDismissListener;
import com.orhanobut.dialogplus.OnItemClickListener;
import com.orhanobut.dialogplus.ViewHolder;

public class JDialogTestActivity extends AppCompatActivity {

    private RadioGroup radioGroup;
    private CheckBox headerCheckBox;
    private CheckBox footerCheckBox;
    private CheckBox expandedCheckBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jdialog_test);

        radioGroup = (RadioGroup) findViewById(R.id.radio_group);
        headerCheckBox = (CheckBox) findViewById(R.id.header_check_box);
        footerCheckBox = (CheckBox) findViewById(R.id.footer_check_box);
        expandedCheckBox = (CheckBox) findViewById(R.id.expanded_check_box);

        findViewById(R.id.button_bottom).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(
                        radioGroup.getCheckedRadioButtonId(),
                        Gravity.BOTTOM,
                        headerCheckBox.isChecked(),
                        footerCheckBox.isChecked(),
                        expandedCheckBox.isChecked()
                );
            }
        });

        findViewById(R.id.button_center).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(
                        radioGroup.getCheckedRadioButtonId(),
                        Gravity.CENTER,
                        headerCheckBox.isChecked(),
                        footerCheckBox.isChecked(),
                        expandedCheckBox.isChecked()
                );
            }
        });

        findViewById(R.id.button_top).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(
                        radioGroup.getCheckedRadioButtonId(),
                        Gravity.TOP,
                        headerCheckBox.isChecked(),
                        footerCheckBox.isChecked(),
                        expandedCheckBox.isChecked()
                );
            }
        });

    }

    private void showDialog(int holderId, int gravity, boolean showHeader, boolean showFooter, boolean expanded) {
        boolean isGrid;
        JDialog.CONTENT_TYPE contentType;
        boolean cancelable = true;
        switch (holderId) {
            case R.id.basic_holder_radio_button:
                isGrid = false;
                contentType = JDialog.CONTENT_TYPE.CUSTOM_CONTENT;
                break;
            case R.id.list_holder_radio_button:
                isGrid = false;
                contentType = JDialog.CONTENT_TYPE.LIST;
                break;
            default:
                isGrid = true;
                contentType = JDialog.CONTENT_TYPE.GRID;
        }

        OnClickListener clickListener = new OnClickListener() {
            @Override
            public void onClick(DialogPlus dialog, View view) {
                        switch (view.getId()) {
                          case R.id.header_container:
                            Toast.makeText(JDialogTestActivity.this, "Header clicked", Toast.LENGTH_LONG).show();
                            break;
                          case R.id.like_it_button:
                            Toast.makeText(JDialogTestActivity.this, "We're glad that you like it", Toast.LENGTH_LONG).show();
                            break;
                          case R.id.love_it_button:
                            Toast.makeText(JDialogTestActivity.this, "We're glad that you love it", Toast.LENGTH_LONG).show();
                            break;
                          case R.id.footer_confirm_button:
                            Toast.makeText(JDialogTestActivity.this, "Confirm button clicked", Toast.LENGTH_LONG).show();
                            break;
                          case R.id.footer_close_button:
                            Toast.makeText(JDialogTestActivity.this, "Close button clicked", Toast.LENGTH_LONG).show();
                            break;
                        }
                        dialog.dismiss();
            }
        };

        OnItemClickListener itemClickListener = new OnItemClickListener() {
            @Override
            public void onItemClick(DialogPlus dialog, Object item, View view, int position) {
                TextView textView = (TextView) view.findViewById(R.id.text_view);
                String clickedAppName = textView.getText().toString();
                        dialog.dismiss();
                        Toast.makeText(JDialogTestActivity.this, clickedAppName + " clicked", Toast.LENGTH_LONG).show();
            }
        };

        OnDismissListener dismissListener = new OnDismissListener() {
            @Override
            public void onDismiss(DialogPlus dialog) {
                        Toast.makeText(JDialogTestActivity.this, "dismiss listener invoked!", Toast.LENGTH_SHORT).show();
            }
        };

        OnCancelListener cancelListener = new OnCancelListener() {
            @Override
            public void onCancel(DialogPlus dialog) {
                        Toast.makeText(JDialogTestActivity.this, "cancel listener invoked!", Toast.LENGTH_SHORT).show();
            }
        };

        DialogAdapter adapter= new DialogAdapter(JDialogTestActivity.this, isGrid);
        JDialog.showDialog(JDialogTestActivity.this, contentType, gravity,
                R.layout.dialog_content_layout, clickListener,
                adapter, 3, itemClickListener,
                cancelListener, dismissListener,
                showHeader, R.layout.dialog_header_layout,
                showFooter, R.layout.dialog_footer_layout,
                expanded, cancelable);
    }
}
