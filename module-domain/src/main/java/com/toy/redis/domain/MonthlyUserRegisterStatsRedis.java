package com.toy.redis.domain;

import com.toy.admin.domain.MonthlyUserRegisterStats;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.redis.core.RedisHash;

import javax.persistence.Id;
import java.util.List;

@Getter
@RedisHash(value = "monthlyUserRegisterStatsRedis", timeToLive = 10)
public class MonthlyUserRegisterStatsRedis {
    @Id
    private String id;
    private List<MonthlyUserRegisterStats> userRegisterStats;

    @Builder
    public MonthlyUserRegisterStatsRedis(String id, List<MonthlyUserRegisterStats> userRegisterStats) {
        this.id = id;
        this.userRegisterStats = userRegisterStats;
    }
}
