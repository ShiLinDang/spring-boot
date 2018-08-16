package com.baidu.mq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.QueueingConsumer;

/**
 * Author: dangsl
 * Date: 2018/4/27
 * Description:消息消费者
 */
public class Consumer {

    private static String queueName = "queue2";

    public static void main(String[] args) throws Exception {

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("47.98.238.150");
        factory.setUsername("admin");
        factory.setPassword("admin");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        // 声明队列，主要为了防止消息接收者先运行此程序，队列还不存在时创建队列。
        channel.queueDeclare(queueName, true, false, false, null);
        System.out.println(Consumer.class.hashCode()
                + " [*] Waiting for messages. To exit press CTRL+C");

        // 创建队列消费者
        QueueingConsumer consumer = new QueueingConsumer(channel);

        // 设置最大服务消息接收数量
        int prefetchCount = 1;
        channel.basicQos(prefetchCount);

        boolean ack = false; // 是否自动确认消息被成功消费
        channel.basicConsume(queueName, ack, consumer); // 指定消费队列

        while (true) {
            // nextDelivery是一个阻塞方法（内部实现其实是阻塞队列的take方法）
            QueueingConsumer.Delivery delivery = consumer.nextDelivery();
            String message = new String(delivery.getBody());
            System.out.println(" [x] Received '" + message + "'");

            channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
            Thread.sleep(2000);
        }

    }

}
