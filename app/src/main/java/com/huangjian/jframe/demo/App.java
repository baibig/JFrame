package com.huangjian.jframe.demo;

import android.app.Application;

import com.huangjian.jframe.utils.jlog.JLog;

/**
 * Description:
 * Author: huangjian
 * Date: 16/2/24 下午7:26.
 */
public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        JLog.init(BuildConfig.DEBUG);
    }
}
