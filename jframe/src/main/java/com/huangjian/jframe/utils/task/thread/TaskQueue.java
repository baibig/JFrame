package com.huangjian.jframe.utils.task.thread;

import android.os.Handler;
import android.os.Message;

import com.huangjian.jframe.utils.log.JLog;
import com.huangjian.jframe.utils.task.TaskItem;
import com.huangjian.jframe.utils.task.TaskListListener;
import com.huangjian.jframe.utils.task.TaskObjectListener;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Executor;

/**
 * Description:
 * Author: huangjian
 * Date: 16/3/9 下午1:47.
 */
public class TaskQueue extends Thread {

    /** 等待执行的任务. 用 LinkedList增删效率高*/
    private LinkedList<TaskItem> taskItemList = null;

    /** 停止的标记. */
    private boolean quit = false;

    /**  存放返回的任务结果. */
    private HashMap<String,Object> result;

    /** 执行完成后的消息句柄. */
    private Handler handler = new Handler() {
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
     *
     * 构造.
     * @return
     */
    public static TaskQueue newInstance() {
        TaskQueue taskQueue = new TaskQueue();
        return taskQueue;
    }

    /**
     * 构造执行线程队列.
     */
    private TaskQueue() {
        quit = false;
        taskItemList = new LinkedList<TaskItem>();
        result = new HashMap<String,Object>();
        //从线程池中获取
        Executor mExecutorService  = JThreadFactory.getExecutorService();
        mExecutorService.execute(this);
    }

    /**
     * 开始一个执行任务.
     *
     * @param item 执行单位
     */
    public void execute(TaskItem item) {
        addTaskItem(item);
    }


    /**
     * 开始一个执行任务并清除原来队列.
     * @param item 执行单位
     * @param cancel 清空之前的任务
     */
    public void execute(TaskItem item,boolean cancel) {
        if(cancel){
            cancel(true);
        }
        addTaskItem(item);
    }

    /**
     * 描述：添加到执行线程队列.
     *
     * @param item 执行单位
     */
    private synchronized void addTaskItem(TaskItem item) {
        taskItemList.add(item);
        //添加了执行项就激活本线程
        this.notify();

    }

    /**
     * 描述：线程运行.
     *
     * @see java.lang.Thread#run()
     */
    @Override
    public void run() {
        while(!quit) {
            try {
                while(taskItemList.size() > 0){

                    TaskItem item = taskItemList.remove(0);
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
                        //交由UI线程处理
                        Message msg = handler.obtainMessage();
                        msg.obj = item;
                        handler.sendMessage(msg);
                    }

                    //停止后清空
                    if(quit){
                        taskItemList.clear();
                        return;
                    }
                }
                try {
                    //没有执行项时等待
                    synchronized(this) {
                        this.wait();
                    }
                } catch (InterruptedException e) {
                    JLog.e("TaskQueue", "收到线程中断请求");
                    e.printStackTrace();
                    //被中断的是退出就结束，否则继续
                    if (quit) {
                        taskItemList.clear();
                        return;
                    }
                    continue;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 描述：终止队列释放线程.
     *
     * @param interrupt the may interrupt if running
     */
    public void cancel(boolean interrupt){
        try {
            quit  = true;
            if(interrupt){
                interrupted();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public LinkedList<TaskItem> getTaskItemList() {
        return taskItemList;
    }

    public int getTaskItemListSize() {
        return taskItemList.size();
    }

}
