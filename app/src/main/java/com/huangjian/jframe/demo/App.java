package com.huangjian.jframe.demo;

import android.app.Application;

import com.huangjian.jframe.http.JOkHttp;
import com.huangjian.jframe.http.JOkHttpConfiguration;
import com.huangjian.jframe.log.JLog;
import com.huangjian.jframe.log.Settings;

/**
 * Description:
 * Author: huangjian
 * Date: 16/2/24 下午7:26.
 */
public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        JLog.initialize(Settings.getInstance());
        JOkHttpConfiguration.Builder builder = new JOkHttpConfiguration.Builder();
        builder.setDebug(BuildConfig.DEBUG);
        JOkHttp.getInstance().init(builder.build());
    }
}
