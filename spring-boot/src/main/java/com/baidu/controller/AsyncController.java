package com.baidu.controller;

import com.baidu.service.TestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by dangsl on 2018/3/21.
 */
@EnableAsync
@RestController
@RequestMapping("async")
public class AsyncController {

    @Autowired
    private TestService testService;

    private Logger logger = LoggerFactory.getLogger(Logger.class);

    /**
     * SpirngBoot的异步任务
     * @return
     */
    @RequestMapping(value = "test5",method = RequestMethod.GET)
    public String test5(){
        testService.getTest1();
        logger.info("============>"+Thread.currentThread().getName());
        return "spring-boot异步,解析完成";
    }
}
