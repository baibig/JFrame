package com.huangjian.jframe.utils;

import android.content.Context;

/**
 * Description: Resource根据资源名获取资源id工具类
 * Author: huangjian
 * Date: 16/2/22 下午2:36.
 */
public class ResourceUtils {
    /**
     * 获取 layout 布局文件
     * @param context Context
     * @param resName  layout xml 的文件名
     * @return layout
     */
    public static int getLayoutId(Context context, String resName) {
        return context.getResources().getIdentifier(resName, "layout",
                context.getPackageName());
    }

    /**
     * 获取 string 值
     * @param context  Context
     * @param resName   string name的名称
     * @return string
     */
    public static int getStringId(Context context, String resName) {
        return context.getResources().getIdentifier(resName, "string",
                context.getPackageName());
    }

    /**
     * 获取 drawable
     * @param context  Context
     * @param resName drawable 的名称
     * @return drawable
     */
    public static int getDrawableId(Context context, String resName) {
        return context.getResources().getIdentifier(resName,
                "drawable", context.getPackageName());
    }

    /**
     * 获取 mipmap
     * @param context
     * @param resName
     * @return
     */
    public static int getMipmapId(Context context, String resName) {
        return context.getResources().getIdentifier(resName,
                "mipmap", context.getPackageName());
    }


    /**
     * 获取 style
     * @param context Context
     * @param resName  style的名称
     * @return style
     */
    public static int getStyleId(Context context, String resName) {
        return context.getResources().getIdentifier(resName, "style",
                context.getPackageName());
    }

    /**
     * 获取 styleable
     * @param context  Context
     * @param resName  styleable 的名称
     * @return styleable
     */
    public static Object getStyleableId(Context context, String resName){
        return context.getResources().getIdentifier(resName, "styleable",
                context.getPackageName());
    }


    /**
     * 获取 anim
     * @param context  Context
     * @param resName  anim xml 文件名称
     * @return anim
     */
    public static int getAnimId(Context context, String resName) {
        return context.getResources().getIdentifier(resName, "anim",
                context.getPackageName());
    }

    /**
     * 获取 id
     * @param context Context
     * @param resName id 的名称
     * @return
     */
    public static int getId(Context context, String resName) {
        return context.getResources().getIdentifier(resName, "id",
                context.getPackageName());
    }

    /**
     * color
     * @param context  Context
     * @param resName  color 名称
     * @return
     */
    public static int getColorId(Context context, String resName) {
        return context.getResources().getIdentifier(resName, "color",
                context.getPackageName());
    }
}

