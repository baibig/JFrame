package com.huangjian.jframe.demo;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.huangjian.jframe.ui.adapter.JRecyclerViewAdapter;
import com.huangjian.jframe.ui.view.image.JImageView;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewTestActivity extends AppCompatActivity {
    RecyclerView mRecyclerView;
    MyAdapter myAdapter;
    List<MyDataModel> mData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview_test);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview_test);
        mData = new ArrayList<>();
        for (int i = 0; i< 20; i ++) {
            MyDataModel dataModel = new MyDataModel();
            dataModel.text = "条目 ：" + i;
            mData.add(dataModel);
        }
        myAdapter = new MyAdapter(mData, RecyclerViewTestActivity.this);
        mRecyclerView.setAdapter(myAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(RecyclerViewTestActivity.this));
    }

    class MyAdapter extends JRecyclerViewAdapter<MyDataModel> {
        public MyAdapter(List<MyDataModel> data, Context context) {
            super(data, context);
        }

        @Override
        public void onBind(JRecyclerViewAdapter<MyDataModel>.InnerViewHolder holder, final int position) {
            View itemView = holder.getView(R.id.item_list_test);
            JImageView imageView = holder.getView(R.id.item_list_test_img);
            TextView textView = holder.getView(R.id.item_list_test_txt);
            MyDataModel dataModel = getItem(position);
            textView.setText(dataModel.text);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(RecyclerViewTestActivity.this, "Clicked position " + position, Toast.LENGTH_SHORT).show();
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
