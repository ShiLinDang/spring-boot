package com.baidu.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 定时任务处理
 * Created by dangsl on 2018/4/10.
 */
@Component
public class LimitTimeController {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Scheduled(fixedRate = 100000)
    public void printTime(){
        logger.info("定时任务，现在时间："+System.currentTimeMillis());
    }
}
