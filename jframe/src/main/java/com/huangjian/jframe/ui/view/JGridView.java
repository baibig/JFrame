package com.huangjian.jframe.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.GridView;

/**
 * Created by huangjian on 16/2/22.
 *
 * 重写onMeasure方法,去除GridView右侧滑动条, 有多长显示多长,修复与ScrollView嵌套时GridView显示不全的bug.
 *
 */
public class JGridView extends GridView{

    public JGridView(Context context) {
        super(context);
    }

    public JGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public JGridView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = View.MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, View.MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}
