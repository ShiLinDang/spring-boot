package com.baidu.threadpractice;

/**
 * @Auther: dangshilin
 * @Date: 2018/11/15
 */
public class MyThread1 extends Thread{
    @Override
    public void run(){
        try {
            for (int i = 0; i <10 ; i++) {
                int time = (int) (Math.random()*100);
                Thread.sleep(time);
                System.out.println("run="+Thread.currentThread().getName());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
