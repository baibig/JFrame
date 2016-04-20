package com.huangjian.jframe.task.thread;

import android.os.Handler;

import com.huangjian.jframe.utils.DeviceUtils;
import com.huangjian.jframe.task.TaskItem;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Description: 简单封装了一个线程池
 * Author: huangjian
 * Date: 16/3/9 下午1:49.
 */
public class TaskPool {

    /** 单例对象 The http pool. */
    private static volatile TaskPool mInstance = null;

    /** 线程执行器. */
    private static ExecutorService mExecutorService = null;

    /** 保存线程数量 . */
    private static final int CORE_POOL_SIZE = 5;

    private Handler mHandler;

    /**
     * 构造线程池.
     */
    private TaskPool() {
        mExecutorService = Executors.newFixedThreadPool(DeviceUtils.getNumCores() * CORE_POOL_SIZE, new ThreadFactory() {
            private final AtomicInteger threadCount = new AtomicInteger(0);
            @Override
            public Thread newThread(Runnable runnable) {
                return new Thread(runnable, "JThread # " + threadCount.getAndIncrement());
            }
        });
        mHandler = new Handler();
    }

    public static TaskPool getInstance() {
        TaskPool tmp = mInstance;
        if (tmp == null) {
            synchronized (TaskPool.class) {
                tmp = mInstance;
                if (tmp == null) {
                    tmp = new TaskPool();
                    mInstance = tmp;
                }
            }
        }
        return tmp;
    }

    /**
     * 执行任务.
     * @param item
     */
    public void addTask(final TaskItem item) {
        mExecutorService.execute(new Runnable() {
            public void run() {
                try {
                    //定义了回调
                    if (item.getCallback() != null) {
                        item.getCallback().prepare();
                        final Object response = item.getCallback().execute();
                        mHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                item.getCallback().update(response);
                            }
                        });

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void shutdown() {
        mExecutorService.shutdown();
    }

}
