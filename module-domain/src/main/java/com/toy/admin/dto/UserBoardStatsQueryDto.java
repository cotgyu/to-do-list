package com.toy.admin.dto;

import com.querydsl.core.annotations.QueryProjection;
import com.toy.admin.domain.UserBoardStats;
import lombok.Data;

@Data
public class UserBoardStatsQueryDto {

    private Long count;
    private Long userId;
    private String name;

    @QueryProjection
    public UserBoardStatsQueryDto(Long count, Long userId, String name) {
        this.count = count;
        this.userId = userId;
        this.name = name;
    }

    public UserBoardStatsQueryDto(UserBoardStats userBoardStats) {
        this.count = userBoardStats.getCount();
        this.userId = userBoardStats.getUserId();
        this.name = userBoardStats.getName();
    }
}
