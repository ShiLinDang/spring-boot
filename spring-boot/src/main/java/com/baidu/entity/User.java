package com.baidu.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Mypc on 2018/3/26 0026.
 */
@Entity
@Table(name = "user")
public class User implements Serializable{
    private static final long serialVersionUID = -1658171344290937389L;
    // id
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id",nullable = false)
    private String id;
    // 姓名
    @Column(name = "name",nullable = true)
    private String name;
    // 电话
    @Column(name = "mobile",nullable = true)
    private String mobile;
    // 昵称
    @Column(name = "nick_name",nullable = true)
    private String nickName;

    // 分数
    private Integer score;
    // 尺码
    private Integer size;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }
}
