package com.huangjian.jframe.ui.base;

import android.widget.Toast;

/**
 * Description:对activity进行了简单封装,包括toast
 * Author: huangjian
 * Date: 16/3/8 下午4:54.
 * todo: 封装ToolBar
 */
public class JBaseActivity extends BaseActivity{
    /**
     * 弹出toast 显示时长short
     * @param pMsg
     */
    protected void showToastMsgShort(String pMsg) {
        Toast.makeText(this, pMsg, Toast.LENGTH_SHORT).show();
    }
    /**
     * 弹出toase 显示时长long
     * @param pMsg
     */
    protected void showToastMsgLong(String pMsg) {
        Toast.makeText(this, pMsg, Toast.LENGTH_LONG).show();
    }
}
