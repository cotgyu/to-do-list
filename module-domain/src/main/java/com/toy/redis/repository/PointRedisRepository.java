package com.toy.redis.repository;

import com.toy.redis.domain.Point;
import org.springframework.data.repository.CrudRepository;

public interface PointRedisRepository extends CrudRepository<Point, String> {
}
