package com.baidu.controller;

import cn.hutool.core.bean.BeanUtil;
import com.baidu.Excell.BeanUtils;
import com.baidu.Util.IgnorePropertiesUtil;
import com.baidu.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 定时任务处理
 * Created by dangsl on 2018/4/10.
 */
@Component
public class LimitTimeController {
//    private final Logger logger = LoggerFactory.getLogger(getClass());
//    @Scheduled(fixedRate = 100000)
//    public void printTime(){
//        logger.info("定时任务，现在时间："+System.currentTimeMillis());
//    }

    public static void main(String[] args) {
        User user = new User();
        user.setName("李白");
        user.setScore(80);
        User user1 = new User();
        user1.setNickName("啦啦");
        BeanUtil.copyProperties(user,user1);
        BeanUtil.copyProperties(user,user1,IgnorePropertiesUtil.getNullPropertyNames(user));
        System.out.println("************************");
    }
}
