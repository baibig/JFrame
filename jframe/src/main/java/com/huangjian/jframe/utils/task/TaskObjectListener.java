package com.huangjian.jframe.utils.task;

/**
 * Description:
 * Author: huangjian
 * Date: 16/3/9 下午1:46.
 */
public abstract class TaskObjectListener extends TaskListener {

    /**
     * 执行开始
     * @return 返回的结果对象
     */
    public abstract <T extends Object> T getObject();

    /**
     * 执行开始后调用.
     * @param obj
     */
    public abstract <T extends Object> void update(T obj);


}
