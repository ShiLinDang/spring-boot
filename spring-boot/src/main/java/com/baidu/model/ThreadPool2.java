package com.baidu.model;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by dangsl on 2018/3/22.
 */
public class ThreadPool2 {
    public static void main(String[] args) {
        // 创建一个定长的线程池对象,参数7,代表有7个线程的线程池
        ExecutorService executorService = Executors.newFixedThreadPool(7);
        // 创建线程任务对象
        MyThread3 myThread3 = new MyThread3();
        // 从线程池中获取线程对象
        Future<?> submit = executorService.submit(myThread3);
        System.out.println("=====================================================");
        // 从线程池中再获取一个线程对象
        Future<?> submit1 = executorService.submit(myThread3);
        // 关闭线程池
        executorService.shutdown();
    }
}
