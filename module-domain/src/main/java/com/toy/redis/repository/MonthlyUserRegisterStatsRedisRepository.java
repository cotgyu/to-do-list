package com.toy.redis.repository;

import com.toy.redis.domain.MonthlyUserRegisterStatsRedis;
import org.springframework.data.repository.CrudRepository;

public interface MonthlyUserRegisterStatsRedisRepository extends CrudRepository<MonthlyUserRegisterStatsRedis, String> {
}
