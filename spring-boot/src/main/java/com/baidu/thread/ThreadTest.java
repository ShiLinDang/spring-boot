package com.baidu.thread;

import com.baidu.model.MyThread2;

/**
 * Created by dangsl on 2018/3/22.
 */
public class ThreadTest {
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            System.out.println(Thread.currentThread().getName()+"做"+ i +"次俯卧撑");
        }

        // System.out.println(Thread.currentThread().getName()+"干点别的");// 一个线程在干两件事

        // MyThread myThread = new MyThread();
        // myThread.start(); // 两个线程在干两件事;开启新的线程并调用run方法
        // myThread.run(); // 仅仅是调用run方法,并没有开启新的线程

         MyThread2 myThread2 = new MyThread2();
         Thread thread = new Thread(myThread2);
         thread.start(); // 开启新的线程并调用run方法
         // thread.run(); // 仅仅是调用run方法,并没有开启新的线程
    }
}
