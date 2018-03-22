package com.baidu.model;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by dangsl on 2018/3/22.
 */
public class ThreadPool {
    public static void main(String[] args) {
        // 创建一个定长的线程池对象,参数7,代表有7个线程的线程池
        ExecutorService executorService = Executors.newFixedThreadPool(7);
        // 创建线程任务对象
        MyThread2 myThread2 = new MyThread2();
        // 从线程池中获取线程对象并执行
        Future<?> submit = executorService.submit(myThread2);
        System.out.println("=====================================================");
        // 从线程池中再获取一个线程对象并执行
        Future<?> submit1 = executorService.submit(myThread2);
        // 关闭线程池
        executorService.shutdown();
    }
}
