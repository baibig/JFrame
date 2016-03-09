package com.huangjian.jframe.utils.task;

/**
 * Description:
 * Author: huangjian
 * Date: 16/3/9 下午1:42.
 */
public class TaskListener {

    /**
     * 执行开始.
     *
     * @return 返回的结果对象
     */
    public void get() {
    };

    /**
     * 执行开始后调用.
     * */
    public void update() {
    };

    /**
     * 监听进度变化.
     *
     * @param values
     *            the values
     */
    public void onProgressUpdate(Integer... values) {
    };

}
