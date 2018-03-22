package com.baidu.model;

/**
 * Created by dangsl on 2018/3/22.
 */
public class MyThread2 implements Runnable{
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+"还是干点别的吧!!!");
    }
}
