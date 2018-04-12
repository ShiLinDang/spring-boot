package com.baidu.controller;

import com.baidu.config.MqConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Mypc on 2018/4/3 0003.
 */
@Controller
@RequestMapping("web")
public class WebController {

    private  RabbitTemplate rabbitTemplate;
    private  Receiver receiver;


    public WebController(RabbitTemplate rabbitTemplate, Receiver receiver) {
        this.rabbitTemplate = rabbitTemplate;
        this.receiver = receiver;
    }

    @RequestMapping(value = "jspIndex",method = RequestMethod.GET)
    public String jspIndex(){
        System.out.println("=========================");

        return "Index2";
    }


    @RequestMapping(value = "testMq",method = RequestMethod.GET)
    public void testMq() throws InterruptedException {
        System.out.println("Sending message...");
        rabbitTemplate.convertAndSend(MqConfig.queueName, "Hello from RabbitMQ!");
    }

}
