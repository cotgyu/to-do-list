package com.toy.board.dto;


import com.toy.board.domain.CardLabel;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CardLabelResponseDto {

    private LabelResponseDto label;
    private String delFlag;

    public CardLabelResponseDto(CardLabel cardLabel) {
        label = new LabelResponseDto(cardLabel.getLabel());
        delFlag = cardLabel.getDelFlag();
    }

}
