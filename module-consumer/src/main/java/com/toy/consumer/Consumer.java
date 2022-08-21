package com.toy.consumer;

import com.toy.user.domain.User;
import com.toy.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class Consumer {

    private final UserRepository userRepository;

    @KafkaListener(id = "lastAccessTime", topics = "LastAccessTimeTopic")
    public void listenLastAccessTime(String payload){
        log.debug(payload);

        // TODO - JSON 형태로 변경, 코드 정리
        String[] split = payload.split(",");
        User user = userRepository.findById(Long.parseLong(split[0])).get();
        user.updateLastAccessTime(LocalDateTime.parse(split[1]));

        userRepository.save(user);
    }
}
