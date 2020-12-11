package com.bobo.hello_spboot.controller;


import com.bobo.hello_spboot.mq.rabbitmq.MsgProducer;
import com.bobo.hello_spboot.mq.rabbitmq.MyProducer;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


@Slf4j
@RestController
@RequestMapping("/rabbit")
@Api(value = "SwaggerValue", tags = {"RabbitMqController"}, description = "RabbitMQ", produces = MediaType.APPLICATION_JSON_VALUE)
public class RabbitMqController {

    @Resource
    private MsgProducer msgProducer;

    @Resource
    private MyProducer myProducer;


    @GetMapping("/sendFanout")
    public void sendFanoutMsg(String msg) {
        msgProducer.send2FanoutTestQueue("this is a test fanout message!");
    }

    @GetMapping(value = "/sendDirect")
    public void sendDirectMsg(){
        msgProducer.send2DirectTestQueue("this is a test direct message!");
    }

    @GetMapping(value = "/sendTopicA")
    public void sendTopicAMsg(){
        msgProducer.send2TopicTestAQueue("this is a test topic aaa message!");
    }

    @GetMapping(value = "/sendTopicB")
    public void sendTopicBMsg(){
        msgProducer.send2TopicTestBQueue("this is a test topic bbb message!");
    }

    @GetMapping("/send2MyQueue")
    public void send2MyQueue() {
        myProducer.produce();
    }




}
