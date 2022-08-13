package com.toy.producer;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;

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
}