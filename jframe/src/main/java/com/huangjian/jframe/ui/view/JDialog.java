package com.huangjian.jframe.ui.view;

import android.content.Context;
import android.widget.BaseAdapter;

import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.DialogPlusBuilder;
import com.orhanobut.dialogplus.GridHolder;
import com.orhanobut.dialogplus.Holder;
import com.orhanobut.dialogplus.ListHolder;
import com.orhanobut.dialogplus.OnCancelListener;
import com.orhanobut.dialogplus.OnClickListener;
import com.orhanobut.dialogplus.OnDismissListener;
import com.orhanobut.dialogplus.OnItemClickListener;
import com.orhanobut.dialogplus.ViewHolder;

/**封装了dialogplus中一些常用的对话框样式。
 * Author: pierce
 * Date: 2016/3/31
 */
public class JDialog {

    /**
     *自定义dialog
     */
    public static void showCustomDialog(Context context, int gravityType,
                                        int contentResId, OnClickListener clickListener,
                                        boolean showHeader, int headerResId,
                                        boolean showFooter, int footerResId,
                                        boolean expanded, boolean cancelable) {
        showDialog(context, CONTENT_TYPE.CUSTOM_CONTENT, gravityType,
                contentResId, clickListener,
                null, 0, null,
                null, null,
                showHeader, headerResId,
                showFooter, footerResId,
                expanded, cancelable);
    }

    /**
     *list dialog
     */
    public static void showListDialog(Context context, int gravityType,
                                      OnClickListener clickListener,
                                      BaseAdapter adapter, OnItemClickListener itemClickListener,
                                      boolean showHeader, int headerResId,
                                      boolean showFooter, int footerResId,
                                      boolean expanded, boolean cancelable) {
        showDialog(context, CONTENT_TYPE.LIST, gravityType,
                0, clickListener, adapter, 0, itemClickListener,
                null, null,
                showHeader, headerResId,
                showFooter, footerResId,
                expanded, cancelable);
    }

    /**
     * grid dialog
     */
    public static void showGridDialog(Context context, int gravityType,
                                      OnClickListener clickListener,
                                      BaseAdapter adapter, int gridColumn, OnItemClickListener itemClickListener,
                                      boolean showHeader, int headerResId,
                                      boolean showFooter, int footerResId,
                                      boolean expanded, boolean cancelable) {
        showDialog(context, CONTENT_TYPE.GRID, gravityType,
                0, clickListener, adapter, gridColumn, itemClickListener,
                null, null,
                showHeader, headerResId,
                showFooter, footerResId,
                expanded, cancelable);
    }

    public static void showDialog(Context context, CONTENT_TYPE contentType, int gravityType,
                                  int contentResId, OnClickListener clickListener,
                                  BaseAdapter adapter, int gridColumn, OnItemClickListener itemClickListener,
                                  OnCancelListener cancelListener, OnDismissListener dismissListener,
                                  boolean showHeader, int headerResId,
                                  boolean showFooter, int footerResId,
                                  boolean expanded, boolean cancelable) {
        Holder holder;
        switch (contentType) {
            case CUSTOM_CONTENT:
                holder = new ViewHolder(contentResId);
                break;
            case LIST:
                holder = new ListHolder();
                break;
            default:
                holder = new GridHolder(gridColumn);
        }
        DialogPlusBuilder builder = DialogPlus.newDialog(context);
        builder.setContentHolder(holder);
        if (null != adapter) {
            builder.setAdapter(adapter);
        }
        builder.setGravity(gravityType);
        if (showHeader) {
            builder.setHeader(headerResId);
        }
        if (showFooter) {
            builder.setFooter(footerResId);
        }
        if (clickListener != null) {
            builder.setOnClickListener(clickListener);
        }
        if (itemClickListener != null) {
            builder.setOnItemClickListener(itemClickListener);
        }
        if (dismissListener != null) {
            builder.setOnDismissListener(dismissListener);
        }
        if (cancelListener != null) {
            builder.setOnCancelListener(cancelListener);
        }
        builder.setCancelable(cancelable).setExpanded(expanded);
        builder.create().show();
    }

    private enum CONTENT_TYPE {
        CUSTOM_CONTENT(0), LIST(1), GRID(2);
        private int value;
        private CONTENT_TYPE(int value) {
            this.value = value;
        }
        public int value() {
            return this.value;
        }
    }

}
