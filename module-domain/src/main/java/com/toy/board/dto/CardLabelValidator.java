package com.toy.board.dto;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

@Component
public class CardLabelValidator {

    public void validate(CardLabelRequestDto cardLabelRequestDto, Errors errors) {
        if (cardLabelRequestDto.getCardId() == null || cardLabelRequestDto.getCardId() < 0) {
            // field 에러
            errors.rejectValue("cardId", "wrongValue", "cardId 은 필수 값 입니다.");
            // global 에러
            errors.reject("cardId", "Values for cardId are wrong");
        }

        if (cardLabelRequestDto.getLabelId() == null || cardLabelRequestDto.getLabelId() < 0) {
            // field 에러
            errors.rejectValue("labelId", "wrongValue", "labelId 은 필수 값 입니다.");
            // global 에러
            errors.reject("labelId", "Values for labelId are wrong");
        }
    }
}
