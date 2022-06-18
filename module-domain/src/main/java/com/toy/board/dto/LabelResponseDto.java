package com.toy.board.dto;


import com.toy.board.domain.Label;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LabelResponseDto {

    private Long labelId;
    private String labelName;
    private String color;

    public LabelResponseDto(Label label) {
        this.labelId = label.getId();
        this.labelName = label.getLabelName();
        this.color = label.getColor();
    }
}
