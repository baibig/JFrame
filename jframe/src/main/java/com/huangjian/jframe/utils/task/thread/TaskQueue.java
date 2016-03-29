package com.huangjian.jframe.utils.task.thread;

import com.huangjian.jframe.utils.task.TaskItem;
import com.huangjian.jframe.utils.task.TaskListListener;
import com.huangjian.jframe.utils.task.TaskObjectListener;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Description:
 * Author: huangjian
 * Date: 16/3/9 下午1:47.
 */
public class TaskQueue {

    private static volatile TaskQueue mInstance;
    private ExecutorService mExecutorService;

    /** 停止的标记. */
    private boolean quit = false;

    /**  存放返回的任务结果. */
    private HashMap<String,Object> result;

    /**
     * 单例
     * @return
     */
    public static TaskQueue getInstance() {
        TaskQueue tmp = mInstance;
        if (tmp == null) {
            synchronized (TaskQueue.class) {
                tmp = mInstance;
                if (tmp == null) {
                    tmp = new TaskQueue();
                    mInstance = tmp;
                }
            }
        }
        return tmp;
    }

    /**
     * 构造执行线程队列.
     */
    private TaskQueue() {
        quit = false;
        result = new HashMap<String,Object>();
        //从线程池中获取
        mExecutorService  = Executors.newSingleThreadExecutor();
    }

    /**
     * 开始一个执行任务.
     *
     * @param item 执行单位
     */
    public void addTask(final TaskItem item) {
        mExecutorService.execute(new Runnable() {
            @Override
            public void run() {
                //定义了回调
                if (item!=null && item.getListener() != null) {
                    if(item.getListener() instanceof TaskListListener){
                        result.put(item.toString(), ((TaskListListener)item.getListener()).getList());
                    }else if(item.getListener() instanceof TaskObjectListener){
                        result.put(item.toString(), ((TaskObjectListener)item.getListener()).getObject());
                    }else{
                        item.getListener().get();
                        result.put(item.toString(), null);
                    }
                    //回调处理数据
                    if(item.getListener() instanceof TaskListListener){
                        ((TaskListListener)item.getListener()).update((List<?>)result.get(item.toString()));
                    }else if(item.getListener() instanceof TaskObjectListener){
                        ((TaskObjectListener)item.getListener()).update(result.get(item.toString()));
                    }else{
                        item.getListener().update();
                    }
                    result.remove(item.toString());
                }
            }
        });
    }

    /**
     * 关闭线程队列。
     */
    public void stop() {
        mExecutorService.shutdown();
    }
}
