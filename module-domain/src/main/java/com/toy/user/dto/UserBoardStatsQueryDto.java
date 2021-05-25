package com.toy.user.dto;

import com.querydsl.core.annotations.QueryProjection;
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
}
