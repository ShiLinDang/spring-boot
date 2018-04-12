package com.baidu.controller;

import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * Created by dangsl on 2018/4/12.
 */
@Component
public class Receiver implements Serializable{

    public void receiveMessage(String message) {
        System.out.println("Received <" + message + ">");
    }
}
