package com.bobo.hello_spboot.mq.rabbitmq;


import cn.hutool.core.date.DateUtil;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;

@Component
public class MyProducer {

    @Resource
    private RabbitTemplate rabbitTemplate;

    public void produce() {
        String message = "北京时间: " + DateUtil.formatDateTime(new Date());
        System.out.println("生产者生产消息: " + message);
        rabbitTemplate.convertAndSend("my_queue", message);
    }

}
