package com.zhizu.kafka.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumer {

    @KafkaListener(topics = { "test" }, concurrency = "3")
    public void consumer(ConsumerRecord<String, String> consumerRecord) {
        System.out.println("消息消费--》" + consumerRecord.value());
    }
}
