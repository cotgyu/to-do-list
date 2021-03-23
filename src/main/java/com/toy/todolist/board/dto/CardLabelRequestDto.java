package com.toy.todolist.board.dto;

import com.toy.todolist.board.domain.CardLabel;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CardLabelRequestDto {

    private Long card_id;
    private Long label_id;
    private String checkFlag;

    public CardLabelRequestDto(Long card_id, Long label_id){
        this.card_id = card_id;
        this.label_id = label_id;
    }
}
