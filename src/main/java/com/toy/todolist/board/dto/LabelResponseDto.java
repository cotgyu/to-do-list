package com.toy.todolist.board.dto;

import com.toy.todolist.board.domain.Label;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LabelResponseDto {

    private Long label_id;
    private String labelName;
    private String color;

    public LabelResponseDto(Label label){
        this.label_id = label.getId();
        this.labelName = label.getLabelName();
        this.color = label.getColor();
    }
}
