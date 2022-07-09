package com.toy.redis.domain;

import com.toy.admin.domain.UserBoardStats;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.redis.core.RedisHash;

import javax.persistence.Id;
import java.util.List;

@Getter
@RedisHash(value = "userBoardStatsRedis", timeToLive = 10)
public class UserBoardStatsRedis {
    @Id
    private String id;
    private List<UserBoardStats> userBoardStats;

    @Builder
    public UserBoardStatsRedis(String id, List<UserBoardStats> userBoardStats) {
        this.id = id;
        this.userBoardStats = userBoardStats;
    }
}
