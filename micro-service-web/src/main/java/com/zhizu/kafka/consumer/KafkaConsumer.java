package com.zhizu.kafka.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumer {

    @KafkaListener(topics = { "test" })
    public void consumer(ConsumerRecord<String, String> consumerRecord) {
        System.out.println("消息消费--》" + consumerRecord.value());
    }
}
