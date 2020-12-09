package com.bobo.hello_spboot.controller;


import com.bobo.hello_spboot.mq.rocketmq.JmsConfig;
import com.bobo.hello_spboot.mq.rocketmq.PayConsumer;
import com.bobo.hello_spboot.mq.rocketmq.PayProducer;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@RestController
public class PayController {

    @Resource
    private PayProducer payProducer;

    @Resource
    private PayConsumer payConsumer;


    @RequestMapping("api/v1/pay_cb")
    public Map<String, Object> callback(String text) {

        Message message = new Message(JmsConfig.TOPIC, "myTag", ("hello RocketMQ " + text).getBytes());
        try {
            SendResult send = payProducer.getProducer().send(message);
            System.out.println("send: " + send);
        } catch (MQClientException | RemotingException | MQBrokerException | InterruptedException e) {
            e.printStackTrace();
        }

        return new HashMap<>();
    }





}
