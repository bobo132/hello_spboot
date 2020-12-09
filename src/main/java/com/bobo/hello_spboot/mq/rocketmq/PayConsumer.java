package com.bobo.hello_spboot.mq.rocketmq;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.Message;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

@Component
public class PayConsumer {

    private DefaultMQPushConsumer consumer;

    private final String consumerGroup = "pay_consumer_group";


    public PayConsumer() {
        consumer = new DefaultMQPushConsumer(consumerGroup);
        consumer.setNamesrvAddr(JmsConfig.NAME_SERVER);

        // 设置消费地点, 从最后一个进行消费 (其实就是消费策略)
        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_LAST_OFFSET);

        // 订阅主题的哪些标签  *代表所有标签
        try {
            consumer.subscribe(JmsConfig.TOPIC, "*");
        } catch (MQClientException e) {
            e.printStackTrace();
        }

        // 注册消费的监听, 并在此监听中消费消息, 并返回消费的状态信息
        consumer.registerMessageListener((MessageListenerConcurrently) (msgs, context) -> {
            try {
                // 获取 message
                // msgs中只收集同一个topic, 同一个tag, 并且key相同的message, 会把不同的消息分别放到不同的队列中
                Message msg = msgs.get(0);
                System.out.printf("%s Receive New Messages: %s %n", Thread.currentThread().getName(), new String(msg.getBody()));

                String topic = msg.getTopic();
                String body = new String(msg.getBody(), StandardCharsets.UTF_8);
                String tags = msg.getTags();
                String keys = msg.getKeys();

                System.out.printf("topic: %s, body: %s, tags: %s, keys: %s %n", topic, body, tags, keys);
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            } catch (Exception e) {
                e.printStackTrace();
                return ConsumeConcurrentlyStatus.RECONSUME_LATER;
            }
        });

        try {
            consumer.start();
        } catch (MQClientException e) {
            e.printStackTrace();
        }

        System.out.println("consumer listen");
    }
}
