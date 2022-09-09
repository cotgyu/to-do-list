package com.toy.common.service;

import com.toy.common.domain.BaseEventVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaProducerException;
import org.springframework.kafka.core.KafkaSendCallback;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;

@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaProducer {

    private final KafkaTemplate<String, Object> kafkaTemplate;


    // TODO - 카프카 로컬환경 안정화 , kafkaTemplate 테스트환경 분리
    public void sendMessage(BaseEventVO eventVO) {
        ListenableFuture<SendResult<String, Object>> future = kafkaTemplate.send(eventVO.getTopicName(), eventVO);
        future.addCallback(new KafkaSendCallback<>() {
            @Override
            public void onSuccess(SendResult<String, Object> result) {
                log.debug("onSuccess: " + result.toString());
            }

            @Override
            public void onFailure(KafkaProducerException ex) {
                log.error("onFailure: " + ex.getMessage());
            }
        });
    }
}
