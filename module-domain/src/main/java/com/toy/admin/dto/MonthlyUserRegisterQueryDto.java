package com.toy.admin.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;

@Data
public class MonthlyUserRegisterQueryDto {

    private int month;
    private Long count;

    @QueryProjection
    public MonthlyUserRegisterQueryDto(int month, Long count) {
        this.month = month;
        this.count = count;
    }
}
