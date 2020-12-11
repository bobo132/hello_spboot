package com.bobo.hello_spboot.mq.rabbitmq;


import com.bobo.hello_spboot.config.mq.RabbitMqConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class MsgProducer {

    @Resource
    private RabbitTemplate rabbitTemplate;

    public void send2FanoutTestQueue(String msg) {
        rabbitTemplate.convertAndSend(RabbitMqConfig.TEST_FANOUT_EXCHANGE, "", msg);
    }

    public void send2DirectTestQueue(String massage){
        rabbitTemplate.convertAndSend(RabbitMqConfig.TEST_DIRECT_EXCHANGE, RabbitMqConfig.DIRECT_ROUTING_KEY, massage);
    }

    public void send2TopicTestAQueue(String massage){
        rabbitTemplate.convertAndSend(RabbitMqConfig.TEST_TOPIC_EXCHANGE, "test.aaa", massage);
    }

    public void send2TopicTestBQueue(String massage){
        rabbitTemplate.convertAndSend(RabbitMqConfig.TEST_TOPIC_EXCHANGE, "test.bbb", massage);
    }


}
