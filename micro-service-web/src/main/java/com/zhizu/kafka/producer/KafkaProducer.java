package com.zhizu.kafka.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.UUID;

@Component
@EnableScheduling
public class KafkaProducer {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    /**
     * 定时任务
     */
    @Scheduled(cron = "0/1 * * * * ?")
    public void send() {
        String message = UUID.randomUUID().toString();
        ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send("test", message);
        future.addCallback(o -> System.out.println("send-消息发送成功：" + message),
                throwable -> System.out.println("消息发送失败：" + message));
    }

}