package com.baidu.model;

import java.util.concurrent.Callable;

/**
 * Created by dangsl on 2018/3/22.
 */
public class MyThread3 implements Callable{
    @Override
    public Object call() throws Exception {
        System.out.println(Thread.currentThread().getName()+"实现Callable接口的线程......");
        return null;
    }
}
