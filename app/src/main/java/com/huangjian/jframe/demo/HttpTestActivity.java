package com.huangjian.jframe.demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.huangjian.jframe.http.FileDownloadCallback;
import com.huangjian.jframe.http.HttpRequest;
import com.huangjian.jframe.http.StringHttpRequestCallback;
import com.huangjian.jframe.log.JLog;

import java.io.File;

public class HttpTestActivity extends AppCompatActivity {

    public static final String TAG = HttpTestActivity.class.getSimpleName();

    Button mBtnDownloadFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_http_test);
        mBtnDownloadFile = (Button) findViewById(R.id.btn_http_down_file);
    }

    public void customHttp(View view) {
        String url = "http://gank.io";
        HttpRequest.get(url, new StringHttpRequestCallback() {
            @Override
            public void onStart() {
                super.onStart();
                JLog.tag(TAG).d("onStart");
            }

            @Override
            protected void onSuccess(String s) {
                super.onSuccess(s);
                JLog.tag(TAG).d("onSuccess\nmessage: %s", s);
            }

            @Override
            public void onFailure(int errorCode, String msg) {
                super.onFailure(errorCode, msg);
                JLog.tag(TAG).d("errorCode : %d \nmsg : %s", errorCode, msg);
            }

            @Override
            public void onFinish() {
                super.onFinish();
                JLog.tag(TAG).d("onFinish");
            }
        });
    }

    public void downFileHttp(View view) {
        String url = "http://219.128.78.33/apk.r1.market.hiapk.com/data/upload/2015/05_20/14/com.speedsoftware.rootexplorer_140220.apk";
        File saveFile = new File("/sdcard/Download/rootexplorer_140220.apk");
        HttpRequest.download(url, saveFile, new FileDownloadCallback() {
            //开始下载
            @Override
            public void onStart() {
                super.onStart();
                JLog.tag(TAG).d("onStart");
            }

            //下载进度
            @Override
            public void onProgress(int progress, long networkSpeed) {
                super.onProgress(progress, networkSpeed);
                mBtnDownloadFile.setText("Down File : " + progress);
            }

            //下载失败
            @Override
            public void onFailure() {
                super.onFailure();
                Toast.makeText(HttpTestActivity.this, "下载失败", Toast.LENGTH_SHORT).show();
            }

            //下载完成（下载成功）
            @Override
            public void onDone() {
                super.onDone();
                Toast.makeText(HttpTestActivity.this, "下载成功", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
