package com.huangjian.jframe.ui.view;

import android.app.DatePickerDialog;
import android.content.Context;

/**
 * Created by huangjian on 16/2/22.
 *
 * 重写onStop方法, 修复在Android 4.x版本上onDateSet执行两次的bug
 *
 */
public class JDatePickerDialog extends DatePickerDialog {
    public JDatePickerDialog(Context context, OnDateSetListener callBack, int year, int monthOfYear, int dayOfMonth) {
        super(context, callBack, year, monthOfYear, dayOfMonth);
    }

    public JDatePickerDialog(Context context, int theme, OnDateSetListener listener, int year, int monthOfYear, int dayOfMonth) {
        super(context, theme, listener, year, monthOfYear, dayOfMonth);
    }

    @Override
    protected void onStop() {

    }
}
