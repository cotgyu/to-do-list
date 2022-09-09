package com.toy.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class TestConsumer {

    @KafkaListener(id = "testId1", topics = "testTopic")
    public void listen(String payload){
        log.debug(payload);
    }
}
