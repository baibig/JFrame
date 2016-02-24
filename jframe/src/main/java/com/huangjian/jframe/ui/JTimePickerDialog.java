package com.huangjian.jframe.ui;

import android.app.TimePickerDialog;
import android.content.Context;

/**
 * Created by huangjian on 16/2/22.
 *
 * 重写onStop方法,修复在Android 4.x版本上onTimeSet执行两次的bug
 */
public class JTimePickerDialog extends TimePickerDialog {
    public JTimePickerDialog(Context context, OnTimeSetListener listener, int hourOfDay, int minute, boolean is24HourView) {
        super(context, listener, hourOfDay, minute, is24HourView);
    }

    public JTimePickerDialog(Context context, int themeResId, OnTimeSetListener listener, int hourOfDay, int minute, boolean is24HourView) {
        super(context, themeResId, listener, hourOfDay, minute, is24HourView);
    }

    @Override
    protected void onStop() {

    }
}
