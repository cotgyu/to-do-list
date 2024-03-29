package com.toy.board.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;

@Data
public class CardLabelQueryDto {

    private Long labelId;
    private String labelName;
    private String color;
    private String checkFlag;

    @QueryProjection
    public CardLabelQueryDto(Long labelId, String labelName, String color, String checkFlag) {
        this.labelId = labelId;
        this.labelName = labelName;
        this.color = color;
        this.checkFlag = checkFlag;
    }
}
