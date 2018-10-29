package com.baidu.controller.optional;

import com.baidu.entity.User;

import java.util.Optional;

/**
 * Optional测试类
 */
public class OptionalTest {
    public static void main(String[] args) {
        User user = new User();
        // user.setName("lili");
        Integer length = getLength(user);
        System.out.println(length);
    }

    private static Integer getLength(User user){
        return Optional.ofNullable(user.getName()).map(e -> e.split("i").length).orElse(0);
    }
}
