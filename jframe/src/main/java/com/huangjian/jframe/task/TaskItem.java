package com.huangjian.jframe.task;

/**
 * Description:
 * Author: huangjian
 * Date: 16/3/9 下午1:42.
 */
public class TaskItem {
    /** 执行完成的回调接口. */
    private TaskCallback callback;

    /**
     * Instantiates a new ab task item.
     */
    public TaskItem() {
        super();
    }

    /**
     * Instantiates a new ab task item.
     *
     * @param callback the callback
     */
    public TaskItem(TaskCallback callback) {
        super();
        this.callback = callback;
    }

    /**
     * Gets the callback.
     *
     * @return the callback
     */
    public TaskCallback getCallback() {
        return callback;
    }

}
