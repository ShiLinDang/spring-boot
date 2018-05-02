package com.baidu.controller;

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
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Scheduled(fixedRate = 100000)
    public void printTime(){
        logger.info("定时任务，现在时间："+System.currentTimeMillis());
    }

    public static void main(String[] args) {
        List<User> userList = new ArrayList<>();
        List<User> userAdd = new ArrayList<>();
        User user1 = new User();
        user1.setNickName("李白1");
        user1.setScore(21);
        user1.setSize(1);
        userList.add(user1);

        User user2 = new User();
        user2.setNickName("李白2");
        user2.setScore(22);
        user2.setSize(2);
        userList.add(user2);

        User user3 = new User();
        user3.setNickName("李白3");
        user3.setScore(20);
        user3.setSize(3);
        userList.add(user3);

        User user4 = new User();
        user4.setNickName("李白4");
        user4.setScore(21);
        user4.setSize(4);
        userList.add(user4);

        User user5 = new User();
        user5.setNickName("李白5");
        user5.setScore(25);
        user5.setSize(5);
        userList.add(user5);
    }
}
