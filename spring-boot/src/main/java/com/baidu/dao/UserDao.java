package com.baidu.dao;

import com.baidu.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Mypc on 2018/3/26 0026.
 */
@Repository
public interface UserDao {
    List<User> getUserList();
}
