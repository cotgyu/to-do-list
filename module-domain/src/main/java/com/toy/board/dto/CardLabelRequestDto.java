package com.toy.board.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CardLabelRequestDto {

    private Long cardId;
    private Long labelId;
    private String checkFlag;

    public CardLabelRequestDto(Long cardId, Long labelId) {
        this.cardId = cardId;
        this.labelId = labelId;
    }
}
