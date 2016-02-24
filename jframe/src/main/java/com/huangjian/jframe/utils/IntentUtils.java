package com.huangjian.jframe.utils;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by huangjian on 16/2/22.
 *
 * 与intent相关的一些工具
 * 包括打电话,发短信
 */
public class IntentUtils {
    /**
     * 启动一个不需要传递参数的activity
     *
     * @param context
     * @param classes
     */
    public static void sendIntent(Context context, Class classes) {
        Intent intent = new Intent();
        intent.setClass(context, classes);
        context.startActivity(intent);
    }

    /**
     * 启动带一个字符串参数的activity
     *
     * @param context
     * @param classes
     * @param key
     * @param value
     */
    public static void sendIntent(Context context, Class classes, String key, String value) {
        Intent intent = new Intent();
        intent.setClass(context, classes);
        intent.putExtra(key, value);
        context.startActivity(intent);
    }


    /**
     * 启动带两个个字符串参数的activity
     *
     * @param context
     * @param classes
     * @param key
     * @param value
     * @param anotherKey
     * @param anotherValue
     */
    public static void sendIntent(Context context, Class classes, String key, String value, String anotherKey, String anotherValue) {
        Intent intent = new Intent();
        intent.setClass(context, classes);
        intent.putExtra(key, value);
        intent.putExtra(anotherKey, anotherValue);
        context.startActivity(intent);
    }

    /**
     * 启动一个带序列化数据的activity
     *
     * @param context
     * @param classes
     * @param key
     * @param value
     */
    public static void sendIntent(Context context, Class classes, String key, Parcelable value) {
        Intent intent = new Intent();
        intent.setClass(context, classes);
        intent.putExtra(key, value);
        context.startActivity(intent);
    }

    /**
     * 启动一个带list数据的activity
     *
     * @param context
     * @param classes
     * @param key
     * @param value
     */
    public static void sendIntent(Context context, Class classes, String key, ArrayList<? extends Parcelable> value) {
        Intent intent = new Intent();
        intent.setClass(context, classes);
        intent.putParcelableArrayListExtra(key, value);
        context.startActivity(intent);
    }

    /**
     * 启动一个带字符串数据和一个list数据的activity
     *
     * @param context
     * @param classes
     * @param key
     * @param value
     * @param anotherKey
     * @param anotherValue
     */
    public static void sendIntent(Context context, Class classes, String key, ArrayList<? extends Parcelable> value, String anotherKey, String anotherValue) {
        Intent intent = new Intent();
        intent.setClass(context, classes);
        intent.putParcelableArrayListExtra(key, value);
        intent.putExtra(anotherKey, anotherValue);
        context.startActivity(intent);
    }

}
