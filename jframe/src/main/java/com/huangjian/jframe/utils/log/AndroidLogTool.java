package com.huangjian.jframe.utils.log;

import android.util.Log;

/**
 * Description: 调用Android系统日志工具, 实现了LogTool接口
 * Author: huangjian
 * Date: 16/2/24 下午 1:41
 */

public class AndroidLogTool implements LogTool {
    @Override
    public void d(String tag, String message) {
        Log.d(tag, message);
    }

    @Override
    public void e(String tag, String message) {
        Log.e(tag, message);
    }

    @Override
    public void w(String tag, String message) {
        Log.w(tag, message);
    }

    @Override
    public void i(String tag, String message) {
        Log.i(tag, message);
    }

    @Override
    public void v(String tag, String message) {
        Log.v(tag, message);
    }

    @Override
    public void wtf(String tag, String message) {
        Log.wtf(tag, message);
    }
}
