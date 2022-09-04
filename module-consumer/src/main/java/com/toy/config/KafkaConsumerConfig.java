package com.toy.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.support.serializer.ErrorHandlingDeserializer;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@EnableKafka
@Configuration
public class KafkaConsumerConfig {

    @Value("${kafka.brokers:127.0.0.1:9092}")
    private String brokers;

    @Value("${kafka.listener.concurrency:1}")
    private Integer concurrency;

    @Bean
    KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, Object>> kafkaListenerContainerFactory(ObjectMapper objectMapper) {
        ConcurrentKafkaListenerContainerFactory<String, Object> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory(objectMapper));
        factory.setConcurrency(concurrency);
        factory.getContainerProperties().setPollTimeout(3000);
        return factory;
    }

    @Bean
    public ConsumerFactory<String, Object> consumerFactory(ObjectMapper objectMapper) {
        Map<String, Object> deserializerConfig = new HashMap<>();
        deserializerConfig.put(JsonDeserializer.TRUSTED_PACKAGES, "*");

        JsonDeserializer<Object> objectJsonDeserializer = new JsonDeserializer<>(objectMapper);
        objectJsonDeserializer.configure(deserializerConfig, false);

        return new DefaultKafkaConsumerFactory<>(consumerConfigs(), new StringDeserializer(), new ErrorHandlingDeserializer<>(objectJsonDeserializer));
    }

    @Bean
    public Map<String, Object> consumerConfigs() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, brokers);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, ErrorHandlingDeserializer.class);

        return props;
    }

    // topic 설정. 자동으로 해당 설정으로 브로커에 추가할 수 있다
    @Bean
    public NewTopic testTopic() {
        return TopicBuilder.name("testTopic")
                .partitions(3)
                .replicas(2)
                .build();
    }

    @Bean
    public NewTopic testTopic2() {
        return TopicBuilder.name("testTopic2")
                .partitions(1)
                .replicas(1)
                .build();
    }

    @Bean
    public NewTopic lastAccessTimeTopic() {
        return TopicBuilder.name("LastAccessTimeTopic")
                .partitions(3)
                .replicas(2)
                .build();
    }
}
