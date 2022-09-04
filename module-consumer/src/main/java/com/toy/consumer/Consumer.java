package com.toy.consumer;

import com.toy.user.domain.User;
import com.toy.user.dto.LastAccessTimeEventVO;
import com.toy.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class Consumer {

    private final UserRepository userRepository;

    @KafkaListener(id = "lastAccessTime", topics = "LastAccessTimeTopic")
    public void listenLastAccessTime(LastAccessTimeEventVO payload) {
        log.debug("payload: " + payload.toString());

        User user = userRepository.findById(payload.getUserId()).orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다. id = " + payload.getUserId()));
        user.updateLastAccessTime(payload.getLastAccessTime());

        userRepository.save(user);
    }
}
