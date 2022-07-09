package com.toy.redis.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RedisService {

    private RedisTemplate redisTemplate;

    // TODO redisTemplate 용 service 생성
    public String getValue(String key) {
        return (String) redisTemplate.opsForValue().get(key);
    }
}
