package com.huangjian.jframe.utils.task.thread;

import android.os.Handler;
import android.os.Message;

import com.huangjian.jframe.utils.task.TaskItem;
import com.huangjian.jframe.utils.task.TaskListListener;
import com.huangjian.jframe.utils.task.TaskObjectListener;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Executor;

/**
 * Description:
 * Author: huangjian
 * Date: 16/3/9 下午1:49.
 */
public class TaskPool {

    /** 单例对象 The http pool. */
    private static TaskPool taskPool = null;

    /** 线程执行器. */
    public static Executor mExecutorService = null;

    /**  存放返回的任务结果. */
    private static HashMap<String,Object> result;

    /** 下载完成后的消息句柄. */
    private static Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            TaskItem item = (TaskItem)msg.obj;
            if(item.getListener() instanceof TaskListListener){
                ((TaskListListener)item.getListener()).update((List<?>)result.get(item.toString()));
            }else if(item.getListener() instanceof TaskObjectListener){
                ((TaskObjectListener)item.getListener()).update(result.get(item.toString()));
            }else{
                item.getListener().update();
            }
            result.remove(item.toString());
        }
    };


    /**
     * 构造线程池.
     */
    private TaskPool() {
        result = new HashMap<String,Object>();
        mExecutorService = JThreadFactory.getExecutorService();
    }

    /**
     * 单例构造图片下载器.
     *
     * @return single instance of AbHttpPool
     */
    public static TaskPool getInstance() {
        if (taskPool == null) {
            taskPool = new TaskPool();
        }
        return taskPool;
    }

    /**
     * 执行任务.
     * @param item the item
     */
    public void execute(final TaskItem item) {
        mExecutorService.execute(new Runnable() {
            public void run() {
                try {
                    //定义了回调
                    if (item.getListener() != null) {
                        if(item.getListener() instanceof TaskListListener){
                            result.put(item.toString(), ((TaskListListener)item.getListener()).getList());
                        }else if(item.getListener() instanceof TaskObjectListener){
                            result.put(item.toString(), ((TaskObjectListener)item.getListener()).getObject());
                        }else{
                            item.getListener().get();
                            result.put(item.toString(), null);
                        }

                        //交由UI线程处理
                        Message msg = handler.obtainMessage();
                        msg.obj = item;
                        handler.sendMessage(msg);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

    }

}