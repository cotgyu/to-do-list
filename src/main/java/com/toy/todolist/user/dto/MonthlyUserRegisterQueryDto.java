package com.toy.todolist.user.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;

@Data
public class MonthlyUserRegisterQueryDto {

    private Integer month;
    private long count;

    @QueryProjection
    public MonthlyUserRegisterQueryDto(int month, long count) {
        this.month = month;
        this.count = count;
    }
}
