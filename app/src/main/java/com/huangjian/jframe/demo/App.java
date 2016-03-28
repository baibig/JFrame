package com.huangjian.jframe.demo;

import android.app.Application;

import com.huangjian.jframe.utils.JLogger;
import com.huangjian.jframe.utils.http.JOkHttp;
import com.huangjian.jframe.utils.http.JOkHttpConfiguration;

/**
 * Description:
 * Author: huangjian
 * Date: 16/2/24 下午7:26.
 */
public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        JLogger.initial();
        JOkHttpConfiguration.Builder builder = new JOkHttpConfiguration.Builder();
        builder.setDebug(BuildConfig.DEBUG);
        JOkHttp.getInstance().init(builder.build());
    }
}
