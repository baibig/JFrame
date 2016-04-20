package com.huangjian.jframe.demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.huangjian.jframe.log.JLog;
import com.huangjian.jframe.task.JTask;
import com.huangjian.jframe.task.TaskCallback;
import com.huangjian.jframe.task.TaskItem;
import com.huangjian.jframe.task.thread.TaskPool;
import com.huangjian.jframe.task.thread.TaskQueue;

import java.util.ArrayList;
import java.util.List;

public class JTaskTestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jtask_test);
        final TaskItem item1 = new TaskItem(new TaskCallback(){
            @Override
            public void prepare() {
                super.prepare();
                JLog.d("task 1 prepare");
            }

            @Override
            public Object execute() {
                JLog.d("task 1 execute");
                return super.execute();
            }

            @Override
            public void update(Object result) {
                super.update(result);
                JLog.d("task 1 done");
            }
        });
        final TaskItem item2 = new TaskItem(new TaskCallback(){
            @Override
            public void prepare() {
                super.prepare();
                JLog.d("task 2 prepare");
            }

            @Override
            public Object execute() {
                JLog.d("task 2 execute");
                return super.execute();
            }

            @Override
            public void update(Object result) {
                super.update(result);
                JLog.d("task 2 done");
            }
        });
        final TaskItem item3 = new TaskItem(new TaskCallback(){
            @Override
            public void prepare() {
                super.prepare();
                JLog.d("task 3 prepare");
            }

            @Override
            public Object execute() {
                JLog.d("task 3 execute");
                return super.execute();
            }

            @Override
            public void update(Object result) {
                super.update(result);
                JLog.d("task 3 done");
            }
        });
        final TaskItem item4 = new TaskItem(new TaskCallback(){
            @Override
            public void prepare() {
                super.prepare();
                JLog.d("task 4 prepare");
            }

            @Override
            public Object execute() {
                JLog.d("task 4 execute");
                return super.execute();
            }

            @Override
            public void update(Object result) {
                super.update(result);
                JLog.d("task 4 done");
            }
        });
        final TaskItem item5 = new TaskItem(new TaskCallback(){
            @Override
            public void prepare() {
                super.prepare();
                JLog.d("task 5 prepare");
            }

            @Override
            public Object execute() {
                JLog.d("task 5 execute");
                return super.execute();
            }

            @Override
            public void update(Object result) {
                super.update(result);
                JLog.d("task 5 done");
            }
        });
        final List<TaskItem> taskItemList = new ArrayList<>();
        taskItemList.add(item1);
        taskItemList.add(item2);
        taskItemList.add(item3);
        taskItemList.add(item4);
        taskItemList.add(item5);
        findViewById(R.id.btn_jtask).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                JTask task = new JTask(item1);
                task.execute();
            }
        });
        findViewById(R.id.btn_task_pool).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (int i = 0; i<5; i++) {
                    TaskPool.getInstance().addTask(taskItemList.get(i));
                }
            }
        });
        findViewById(R.id.btn_task_thread).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (int i = 0; i<5; i++) {
                    TaskQueue.getInstance().addTask(taskItemList.get(i));
                }
            }
        });
    }
}
