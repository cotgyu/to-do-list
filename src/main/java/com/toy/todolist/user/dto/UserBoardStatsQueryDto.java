package com.toy.todolist.user.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;

@Data
public class UserBoardStatsQueryDto {

    private Long count;
    private Long userId;

    @QueryProjection
    public UserBoardStatsQueryDto(Long count, Long userId) {
        this.count = count;
        this.userId = userId;
    }
}
