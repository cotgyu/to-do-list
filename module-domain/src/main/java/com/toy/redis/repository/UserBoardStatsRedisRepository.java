package com.toy.redis.repository;

import com.toy.redis.domain.UserBoardStatsRedis;
import org.springframework.data.repository.CrudRepository;

public interface UserBoardStatsRedisRepository extends CrudRepository<UserBoardStatsRedis, String> {
}
