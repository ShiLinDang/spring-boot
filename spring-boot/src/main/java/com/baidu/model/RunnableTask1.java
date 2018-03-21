package com.baidu.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by dangsl on 2018/3/21.
 */
public class RunnableTask1 implements Runnable{

    private Logger logger = LoggerFactory.getLogger(Logger.class);

    @Override
    public void run() {
        TestModel1 testModel1 = new TestModel1();
        synchronized (testModel1){
            try {
                for (int i = 1; i <= 100; i++) {
                    logger.info(Thread.currentThread().getName() + "----------同步：>" + i);
                    testModel1.wait(200);
                }
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }
}
