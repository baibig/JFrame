package com.huangjian.jframe.utils.task;

import java.util.List;

/**
 * Description:
 * Author: huangjian
 * Date: 16/3/9 下午1:45.
 */
public abstract class TaskListListener extends TaskListener {

    /**
     * 执行开始.
     *
     * @return 返回的结果列表
     */
    public abstract List<?> getList();

    /**
     * 描述：执行完成后回调. 不管成功与否都会执行
     *
     * @param paramList
     *            返回的List
     */
    public abstract void update(List<?> paramList);

}
