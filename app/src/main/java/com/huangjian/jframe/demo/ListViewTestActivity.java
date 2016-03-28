package com.huangjian.jframe.demo;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.huangjian.jframe.ui.adapter.JListAdapter;
import com.huangjian.jframe.utils.image.JImageView;

import java.util.ArrayList;
import java.util.List;

public class ListViewTestActivity extends AppCompatActivity {

    ListView mListView;
    MyListAdapter mAdapter;
    List<MyDataModel> mData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview_test);
        mListView = (ListView) findViewById(R.id.listview_test);
        mData = new ArrayList<>();
        for (int i = 0; i < 20; i ++) {
            MyDataModel dataModel = new MyDataModel();
            dataModel.text = "List 条目 ： " + i;
            mData.add(dataModel);
        }
        mAdapter = new MyListAdapter(ListViewTestActivity.this, mData);
        mListView.setAdapter(mAdapter);
    }

    class MyListAdapter extends JListAdapter<MyDataModel> {
        public MyListAdapter(Context context, List<MyDataModel> list) {
            super(context, list);
        }

        @Override
        public void onBind(ViewHolder holder, final int position) {
            View mView = holder.getView(R.id.item_list_test);
            JImageView mImg = holder.getView(R.id.item_list_test_img);
            TextView mTxt = holder.getView(R.id.item_list_test_txt);
            MyDataModel dataModel = getItem(position);
            mTxt.setText(dataModel.text);
            mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(ListViewTestActivity.this, "Click item : " + position, Toast.LENGTH_SHORT).show();
                }
            });
        }

        @Override
        public int getItemLayoutID(int position) {
            return R.layout.item_list_test;
        }
    }

    class MyDataModel {
        public String url;
        public String text;
    }
}
