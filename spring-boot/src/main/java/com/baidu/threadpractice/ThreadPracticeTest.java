package com.baidu.threadpractice;

/**
 * @Auther: dangshilin
 * @Date: 2018/11/15
 */
public class ThreadPracticeTest {
    public static void main(String[] args) {
        // 线程调用的随机性
        // test1();

        // 线程调用的随机性
        // test2();

        // test3();
        // test4();
    }

    private static void test4() {
        Runnable runnable = new MyRunnable2();
        Thread thread1 = new Thread(runnable);
        Thread thread2 = new Thread(runnable);
        Thread thread3 = new Thread(runnable);
        Thread thread4 = new Thread(runnable);
        Thread thread5 = new Thread(runnable);
        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
        thread5.start();
    }

    private static void test3() {
        Runnable runnable = new MyRunnable();
        Thread thread = new Thread(runnable);
        thread.start();
        System.out.println("運行結束");
    }

    private static void test2() {
        MyThread1 myThread1 = new MyThread1();
        myThread1.setName("myThread1");
        myThread1.start();
        try {
            for (int i = 0; i <10 ; i++) {
                int time = (int) (Math.random()*100);
                Thread.sleep(time);
                System.out.println("main="+Thread.currentThread().getName());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void test1() {
        MyThread myThread = new MyThread();
        myThread.start();
        System.out.println("运行结束！");
    }
}
