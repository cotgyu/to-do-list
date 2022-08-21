package com.toy.producer;

import com.toy.common.domain.MyTopic;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaProducerException;
import org.springframework.kafka.core.KafkaSendCallback;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.kafka.support.SendResult;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.time.LocalDateTime;

@SpringBootTest
class ProducerTest {

    @Autowired
    KafkaTemplate<String, Object> kafkaTemplate;

    @Test
    void produceTest() {
        String message = "testMessage";
        Message<String> testMessage = MessageBuilder.withPayload(message).setHeader(KafkaHeaders.TOPIC, "testTopic").build();

        kafkaTemplate.send(testMessage);
    }

    @Test
    void produceTest2() {
        String message = "testMessage23";

        ListenableFuture<SendResult<String, Object>> future = kafkaTemplate.send("testTopic2", message);
        future.addCallback(new KafkaSendCallback<>() {
            @Override
            public void onSuccess(SendResult<String, Object> result) {
                System.out.println("onSuccess: " + result.toString());
            }

            @Override
            public void onFailure(KafkaProducerException ex) {
                System.out.println("onFailure: " + ex.getMessage());
            }
        });
    }

    @Test
    void produceTest3() {
        String message = "1" + LocalDateTime.now().toString();

        ListenableFuture<SendResult<String, Object>> future = kafkaTemplate.send(MyTopic.LAST_ACCESS_TIME.getTopicName(), message);
        future.addCallback(new KafkaSendCallback<>() {
            @Override
            public void onSuccess(SendResult<String, Object> result) {
                System.out.println("onSuccess: " + result.toString());
            }

            @Override
            public void onFailure(KafkaProducerException ex) {
                System.out.println("onFailure: " + ex.getMessage());
            }
        });
    }
}