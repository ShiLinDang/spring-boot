package com.baidu.controller;

import com.baidu.entity.User;
import com.baidu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Mypc on 2018/3/26 0026.
 */
@RequestMapping("user")
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "testUser",method = RequestMethod.GET)
    public String getNickName(){
        List<User> userList = userService.getUserList();
        String nickName = userList.get(0).getNickName();
        System.out.println(nickName);
        return nickName;
    }

    @RequestMapping(value = "getUserName",method = RequestMethod.GET)
    public String getUser(String id){
        User user = userService.getUser(id);
        return "欢迎我们可爱的:"+user.getName();
    }

}
