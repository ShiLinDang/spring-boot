package com.baidu.threadpractice;

/**
 * @Auther: dangshilin
 * @Date: 2018/11/15
 */
public class MyRunnable2 implements Runnable{

    private  int i = 5;

    @Override
    public void run() {
        System.out.println("i:"+(i--)+"ThreadName:"+Thread.currentThread().getName());
    }
}
