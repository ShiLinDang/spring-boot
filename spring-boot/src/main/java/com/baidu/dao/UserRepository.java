package com.baidu.dao;

import com.baidu.entity.User;
import org.springframework.data.repository.Repository;

import javax.transaction.Transactional;

/**
 * Created by Mypc on 2018/3/29 0029.
 */
@Transactional
public interface UserRepository extends Repository<User,Long> {
    User findById(String id);
}
