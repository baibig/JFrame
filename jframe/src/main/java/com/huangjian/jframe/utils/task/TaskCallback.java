package com.huangjian.jframe.utils.task;

/**
 * Description:
 * Author: huangjian
 * Date: 16/3/9 下午1:42.
 */
public class TaskCallback {

    public void prepare() {}

    /**
     * 执行开始.
     *
     * @return 返回的结果对象
     */
    public Object execute() {
        return null;
    };

    /**
     * 执行完成后调用.
     * */
    public void update(Object result) {
    };
}
