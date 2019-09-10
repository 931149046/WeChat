package com.rabbitmq.controller;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@CrossOrigin
@RestController
public class RabbitMQController {

    @Autowired
    private AmqpTemplate amqpTemplate; //使用这个中间件的接口中的方法进行发送的消息

    @RequestMapping("/sendmq")
    public void send() { //发送在名字为queue的交换机中,交换机再把消息添加在消息队列中
        amqpTemplate.convertAndSend("queue", "这是内容");
    }


    @RabbitListener(queues = "queue")    //监听器监听指定的Queue,这是交换机的名字
    public void processC(String objstr){
        System.out.println("消费端收到的消息:" + objstr);
    }
}
