package com.toy.todolist.board.dto;

import com.toy.todolist.board.domain.Card;
import com.toy.todolist.board.domain.Label;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LabelRequestDto {

    private Long card_id;
    private Long label_id;
    private String labelName;
    private String color;

    public LabelRequestDto(String labelName, String color){
        this.labelName = labelName;
        this.color = color;
    }

    public Label toEntity(){
        return Label.builder()
                .labelName(labelName)
                .color(color)
                .build();
    }



}
