package com.baidu.controller.lambda;

import com.baidu.entity.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class LambdaTest {
    public static void main(String[] args) {
        List<User> userList = new ArrayList<>();
        User user = new User();
        user.setId("1");
        user.setName("李白");
        userList.add(user);

        User user1 = new User();
        user1.setId("2");
        user1.setName("李慕白");
        userList.add(user1);

        User user2 = new User();
        user2.setId("3");
        user2.setName("老王");
        userList.add(user2);

        List<String> ids = Arrays.asList("1", "3");
        List<User> collect = userList.stream().filter(e -> null != e.getId()).filter(e -> ids.contains(e.getId())).collect(Collectors.toList());
        int size = collect.size();
        System.out.println(size);
    }
}
