package com.toy.board.dto;


import com.toy.board.domain.Label;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LabelRequestDto {

    private String labelName;
    private String color;
    private String delFlag;

    public LabelRequestDto(String labelName, String color) {
        this.labelName = labelName;
        this.color = color;
    }

    public Label toEntity() {
        return Label.builder()
                .labelName(labelName)
                .color(color)
                .build();
    }


}
