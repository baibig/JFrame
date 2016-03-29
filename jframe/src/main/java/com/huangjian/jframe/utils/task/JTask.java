package com.huangjian.jframe.utils.task;

import android.os.AsyncTask;

import java.util.List;

/**
 * Description:
 * Author: huangjian
 * Date: 16/3/9 下午1:44.
 * @// TODO: 2016/3/29 刷新进度问题 
 */
public class JTask extends AsyncTask<TaskItem, Integer, TaskItem> {

    /** 监听器. */
    private TaskListener listener;

    /** 结果. */
    private Object result;

    /**
     * 初始化Task.
     */
    public JTask() {
        super();
    }

    /**
     * 实例化.
     */
    public static JTask getInstance() {
        JTask mJTask = new JTask();
        return mJTask;
    }

    /**
     *
     * 执行任务.
     * @param items
     * @return
     */
    @Override
    protected TaskItem doInBackground(TaskItem... items) {
        TaskItem item = items[0];
        this.listener = item.getListener();
        if (this.listener != null) {
            if(this.listener instanceof TaskListListener){
                result = ((TaskListListener)this.listener).getList();
            }else if(this.listener instanceof TaskObjectListener){
                result = ((TaskObjectListener)this.listener).getObject();
            }else{
                this.listener.get();
            }
        }
        return item;
    }

    /**
     *
     * 取消.
     */
    @Override
    protected void onCancelled() {
        super.onCancelled();
    }

    /**
     *
     * 执行完成.
     * @param item
     */
    @Override
    protected void onPostExecute(TaskItem item) {
        if (this.listener != null) {
            if(this.listener instanceof TaskListListener){
                ((TaskListListener)this.listener).update((List<?>)result);
            }else if(this.listener instanceof TaskObjectListener){
                ((TaskObjectListener)this.listener).update(result);
            }else{
                this.listener.update();
            }
        }
    }
}
