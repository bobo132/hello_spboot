package com.bobo.hello_spboot.mq.rabbitmq;


import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class MyConsumer {

    @RabbitHandler
    @RabbitListener(queues = "my_queue")
    public void access(String message) {
        System.out.println("消费者消费信息: " + message);
    }

}
