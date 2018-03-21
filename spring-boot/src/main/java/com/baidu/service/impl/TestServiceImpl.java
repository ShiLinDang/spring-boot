package com.baidu.service.impl;

import com.baidu.model.TestModel1;
import com.baidu.service.TestService;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;


/**
 * Created by dangsl on 2018/3/21.
 */
@Service
public class TestServiceImpl implements TestService{

    private org.slf4j.Logger logger = LoggerFactory.getLogger(org.slf4j.Logger.class);

    @Async // 异步方法的注解
    @Override
    public void getTest1() {
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

    @Override
    public void getTest2() {
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
