package com.toy.todolist.board.dto;

import com.toy.todolist.board.domain.Card;
import com.toy.todolist.board.domain.CardLabel;
import com.toy.todolist.board.domain.CheckItem;
import com.toy.todolist.board.domain.Label;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CardLabelResponseDto {

    private LabelResponseDto label;
    private String delFlag;

    public CardLabelResponseDto(CardLabel cardLabel){
        label = new LabelResponseDto(cardLabel.getLabel());
        delFlag = cardLabel.getDelFlag();
    }

}
