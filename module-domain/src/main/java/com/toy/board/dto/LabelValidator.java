package com.toy.board.dto;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

@Component
public class LabelValidator {

    public void validate(LabelRequestDto labelRequestDto, Errors errors) {
        if (labelRequestDto.getLabelName() == null || labelRequestDto.getLabelName().equals("")) {
            // field 에러
            errors.rejectValue("labelName", "wrongValue", "labelName 은 필수 값 입니다.");
            // global 에러
            errors.reject("labelName", "Values for labelName are wrong");
        }


    }
}
