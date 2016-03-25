package com.huangjian.jframe.demo;

import android.app.IntentService;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.huangjian.jframe.utils.IntentUtils;

public class ListTestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_test);
        findViewById(R.id.btn_recyclerview_test).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IntentUtils.sendIntent(ListTestActivity.this, RecyclerViewTestActivity.class);
            }
        });
        findViewById(R.id.btn_listview_test).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IntentUtils.sendIntent(ListTestActivity.this, ListViewTestActivity.class);
            }
        });
    }
}
