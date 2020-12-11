package com.bobo.hello_spboot.mq.rabbitmq;


import com.bobo.hello_spboot.config.mq.RabbitMqConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

@Slf4j
@Component
public class MsgConsumer {

    @RabbitListener(
            bindings =
                    {
                            @QueueBinding(value = @Queue(value = RabbitMqConfig.FANOUT_QUEUE_NAME, durable = "true"),
                                    exchange = @Exchange(value = RabbitMqConfig.TEST_FANOUT_EXCHANGE, type = "fanout"))
                    })
    @RabbitHandler
    public void processFanoutMsg(Message massage) {
        String msg = new String(massage.getBody(), StandardCharsets.UTF_8);
        log.info("received Fanout message : " + msg);
    }

    @RabbitListener(
            bindings =
                    {
                            @QueueBinding(value = @Queue(value = RabbitMqConfig.FANOUT_QUEUE_NAME1, durable = "true"),
                                    exchange = @Exchange(value = RabbitMqConfig.TEST_FANOUT_EXCHANGE, type = "fanout"))
                    })
    @RabbitHandler
    public void processFanout1Msg(Message massage) {
        String msg = new String(massage.getBody(), StandardCharsets.UTF_8);
        log.info("received Fanout1 message : " + msg);
    }

    @RabbitListener(
            bindings =
                    {
                            @QueueBinding(value = @Queue(value = RabbitMqConfig.DIRECT_QUEUE_NAME, durable = "true"),
                                    exchange = @Exchange(value = RabbitMqConfig.TEST_DIRECT_EXCHANGE),
                                    key = RabbitMqConfig.DIRECT_ROUTING_KEY)
                    })
    @RabbitHandler
    public void processDirectMsg(Message massage) {
        String msg = new String(massage.getBody(), StandardCharsets.UTF_8);
        log.info("received Direct message : " + msg);
    }

    @RabbitListener(
            bindings =
                    {
                            @QueueBinding(value = @Queue(value = RabbitMqConfig.TOPIC_QUEUE_NAME, durable = "true"),
                                    exchange = @Exchange(value = RabbitMqConfig.TEST_TOPIC_EXCHANGE, type = "topic"),
                                    key = RabbitMqConfig.TOPIC_ROUTING_KEY)
                    })
    @RabbitHandler
    public void processTopicMsg(Message massage) {
        String msg = new String(massage.getBody(), StandardCharsets.UTF_8);
        log.info("received Topic message : " + msg);
    }

}
