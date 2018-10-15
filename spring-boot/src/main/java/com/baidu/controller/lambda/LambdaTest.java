package com.baidu.controller.lambda;

import com.baidu.entity.User;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.joining;

public class LambdaTest {
    public static void main(String[] args) {
        List<User> userList = new ArrayList<>();
        User user = new User();
        user.setId("1");
        user.setName("李白");
        user.setScore(null);
        userList.add(user);

        User user1 = new User();
        user1.setId("2");
        user1.setName("李慕白");
        user1.setScore(20);
        userList.add(user1);

        User user2 = new User();
        user2.setId("3");
        user2.setName("老王");
        user2.setScore(30);
        userList.add(user2);

        List<String> ids = Arrays.asList("1","3");
        // 过滤需要的属性对象
        List<User> collect = userList.stream().filter(e -> null != e.getId()).filter(e -> ids.contains(e.getId())).collect(Collectors.toList());
        int size = collect.size();
        System.out.println(size);
        System.out.println("****************************************");
        Integer reduce = userList.stream().filter(e -> null != e.getScore()).map(User::getScore).reduce(0, Integer::sum);
        System.out.println(reduce);
        System.out.println("****************************************");
        String names = userList.stream().filter(e -> null != e.getName()).map(User::getName).collect(joining(","));
        System.out.println(names);
    }
}
