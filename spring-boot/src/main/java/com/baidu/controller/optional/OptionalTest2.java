package com.baidu.controller.optional;

import com.baidu.entity.User;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @Auther: dangshilin
 * @Date: 2018/12/11
 */
public class OptionalTest2 {
    public static void main(String[] args) {
        List<User> list = new ArrayList<>();
        User user = new User();
        user.setName("李白");

        User user2 = new User();
        user2.setName("李慕白");

        list.add(user);
        list.add(user2);

        if (!CollectionUtils.isEmpty(list)){
            String name = Optional.ofNullable(list.get(0)).map(User::getName).orElse("");
            System.out.println(name);
        }
    }
}
