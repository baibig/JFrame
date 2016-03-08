package com.huangjian.jframe.ui.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.huangjian.jframe.utils.ActivityManager;
import com.huangjian.jframe.utils.log.JLog;

/**
 * Description:对activity的简单封装
 * Author: huangjian
 * Date: 16/3/8 下午4:36.
 */
public class BaseActivity extends AppCompatActivity {
    private static final String TAG = BaseActivity.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        JLog.t(TAG).d("BaseActivity onCreate Invoke...");
        ActivityManager.getActivityManager().addActivity(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        JLog.t(TAG).d("BaseActivity onStart Invoke...");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        JLog.t(TAG).d("BaseActivity onRestart Invoke...");
    }

    @Override
    protected void onResume() {
        super.onResume();
        JLog.t(TAG).d("BaseActivity onResume Invoke...");
    }

    @Override
    protected void onPause() {
        super.onPause();
        JLog.t(TAG).d("BaseActivity onPause Invoke...");
    }

    @Override
    protected void onStop() {
        super.onStop();
        JLog.t(TAG).d("BaseActivity onStop Invoke...");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        JLog.t(TAG).d("BaseActivity onDestory Invoke...");
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        JLog.t(TAG).d("BaseActivity onLowMemort Invoke...");
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        JLog.t(TAG).d("BaseActivity onBackPressed Invoke...");
        ActivityManager.getActivityManager().finishActivity(this);
    }
}
