package com.baidu.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Mypc on 2018/4/3 0003.
 */
@RestController
@RequestMapping("web")
public class WebController {

//    private  RabbitTemplate rabbitTemplate;
//    private  Receiver receiver;


//    public WebController(RabbitTemplate rabbitTemplate, Receiver receiver) {
//        this.rabbitTemplate = rabbitTemplate;
//        this.receiver = receiver;
//    }

    @RequestMapping(value = "jspIndex",method = RequestMethod.GET)
    public String jspIndex(){
        System.out.println("=========================");

        return "Index2";
    }


//    @RequestMapping(value = "testMq",method = RequestMethod.GET)
//    public String testMq() throws InterruptedException {
//        System.out.println("Sending message...");
//        rabbitTemplate.convertAndSend(MqConfig.queueName, "Hello from RabbitMQ!");
//        System.out.println("===========================================");
//        return "Sending message...";
//    }
}
