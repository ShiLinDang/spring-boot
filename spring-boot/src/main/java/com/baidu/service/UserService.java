package com.baidu.service;

import com.baidu.entity.User;

import java.util.List;

/**
 * Created by Mypc on 2018/3/26 0026.
 */
public interface UserService {
    /**
     * 使用mybatis
     * @return
     */
    List<User> getUserList();

    /**
     * 使用spring Data JPA
     * @param id
     * @return
     */
    User getUser(String id);

    /**
     * 批量插入用户信息
     * @param list
     */
    void insertUsers(List<User> list);
}
