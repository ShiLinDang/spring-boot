package com.baidu.mq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;

/**
 * Author: dangsl
 * Date: 2018/4/27
 * Description:消息生产者
 */
public class Producer {
    private static String queueName = "queue2";
    public static void main(String[] args) throws Exception{

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("47.98.238.150");
        factory.setUsername("admin");
        factory.setPassword("admin");
        Connection connection = factory.newConnection();

        Channel channel = connection.createChannel();

        channel.queueDeclare(queueName, true, false, false, null);

        for (int i = 0; i < 20; i++) {
            //发送的消息
            String message = "hello world!"+i;
            //往队列中发出一条消息
            channel.basicPublish("", queueName, MessageProperties.PERSISTENT_TEXT_PLAIN, message.getBytes());
            System.out.println(" [x] Sent '" + message + "'");
        }

        //关闭频道和连接
        channel.close();
        connection.close();
    }
}