package com.baidu.service.impl;

import com.baidu.dao.UserDao;
import com.baidu.dao.UserRepository;
import com.baidu.entity.User;
import com.baidu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Mypc on 2018/3/26 0026.
 */
@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserDao userDao;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> getUserList() {
        List<User> userList = userDao.getUserList();
        return userList;
    }

    @Override
    public User getUser(String id) {
        return userRepository.findById(id);
    }

    @Override
    public void insertUsers(List<User> list) {

    }
}
