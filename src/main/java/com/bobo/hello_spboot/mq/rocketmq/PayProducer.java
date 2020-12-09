package com.bobo.hello_spboot.mq.rocketmq;

import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.springframework.stereotype.Component;

@Component
public class PayProducer {

    /**
     * 生产组, 生产者必须在生产组内
     */
    private static final String PRODUCER_GROUP = "pay_group";

    private final DefaultMQProducer producer;

    public PayProducer() {
        producer = new DefaultMQProducer(PRODUCER_GROUP);
        producer.setNamesrvAddr(JmsConfig.NAME_SERVER);    // 指定 nameServer, 多个地址之间以`;`隔开
        start();
    }

    /**
     * 对象在使用之前必须调用一次, 并且只能初始化一次
     */
    private void start() {
        try {
            producer.start();
        } catch (MQClientException e) {
            e.printStackTrace();
        }
    }

    /**
     * 一般在应用上下文, 使用上下文监听器进行关闭
     */
    public void shutdown() {
        producer.shutdown();
    }


    public DefaultMQProducer getProducer() {
        return producer;
    }
}
