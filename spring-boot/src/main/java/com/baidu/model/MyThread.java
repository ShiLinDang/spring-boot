package com.baidu.model;

/**
 * Created by dangsl on 2018/3/22.
 */
public class MyThread extends Thread{
    @Override
    public void run(){
        System.out.println(Thread.currentThread().getName()+"吃点东西......");
    }
}
