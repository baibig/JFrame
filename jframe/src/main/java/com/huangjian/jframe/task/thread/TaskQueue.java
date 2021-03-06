package com.huangjian.jframe.task.thread;

import android.os.Handler;

import com.huangjian.jframe.task.TaskItem;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Description: 简单封装了一个线程队列
 * Author: huangjian
 * Date: 16/3/9 下午1:47.
 */
public class TaskQueue {

    private static volatile TaskQueue mInstance;
    private ExecutorService mExecutorService;
    private Handler mHandler;

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
        mExecutorService  = Executors.newSingleThreadExecutor(new ThreadFactory() {
            private final AtomicInteger threadCount = new AtomicInteger(1);
            @Override
            public Thread newThread(Runnable runnable) {
                return new Thread(runnable, "JThread # " + threadCount.getAndIncrement());
            }
        });
        mHandler = new Handler();
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
                if (item!=null && item.getCallback() != null) {
                    item.getCallback().prepare();
                    final Object response = item.getCallback().execute();
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            item.getCallback().update(response);
                        }
                    });
                }
            }
        });
    }

    /**
     * 关闭线程队列。
     */
    public void shutdown() {
        mExecutorService.shutdown();
    }
}
