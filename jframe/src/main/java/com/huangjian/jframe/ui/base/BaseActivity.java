package com.huangjian.jframe.ui.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.huangjian.jframe.utils.ActivityManager;
import com.huangjian.jframe.utils.log.JLog;

/**
 * Description:对activity的简单封装,提供了getView来简化findViewById,并提供了构建框架
 * Author: huangjian
 * Date: 16/3/8 下午4:36.
 */
public abstract class BaseActivity extends AppCompatActivity {
    private static final String TAG = BaseActivity.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        beforeSetContentView();
        if (getContentViewId() != 0) {
            setContentView(getContentViewId());
        }
        findViews();
        beforeSetViews();
        setViews();
    }

    /**
     * 在setContentView之前触发的方法,如获取intent数据,对activity进行设置.
     */
    protected void beforeSetContentView() {

    }

    /**
     * 如果没有布局，那么就返回0
     *
     * @return activity的布局文件
     */
    protected abstract int getContentViewId();

    /**
     * 找到所有的views,使用getView来找
     */
    protected abstract void findViews();

    /**
     * 在这里初始化设置view的各种资源，比如适配器或各种变量
     */
    protected abstract void beforeSetViews();

    /**
     * 设置所有的view,绑定数据
     */
    protected abstract void setViews();


    /**
     * 通过泛型来简化findViewById
     */
    protected final <E extends View> E getView(int id) {
        try {
            return (E) findViewById(id);
        } catch (ClassCastException ex) {
            JLog.e(TAG, "Could not cast View to concrete class.", ex);
            throw ex;
        }
    }

}
