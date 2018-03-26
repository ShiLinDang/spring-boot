package com.baidu.entity;

import java.io.Serializable;

/**
 * Created by Mypc on 2018/3/26 0026.
 */
public class User implements Serializable{
    private static final long serialVersionUID = -1658171344290937389L;
    // id
    private String id;
    // 姓名
    private String name;
    // 电话
    private String mobile;
    // 昵称
    private String nickName;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
