package com.bobo.hello_spboot;

import com.bobo.hello_spboot.mq.rabbitmq.MyProducer;
import org.junit.Test;

import javax.annotation.Resource;

public class RabbitMqTest extends BaseTest {

    @Resource
    private MyProducer myProducer;

    @Test
    public void f1() {
        myProducer.produce();
    }


}
