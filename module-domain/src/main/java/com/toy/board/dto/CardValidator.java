package com.toy.board.dto;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

@Component
public class CardValidator {

    public void validate(CardRequestDto cardDto, Errors errors) {
        if (cardDto.getCardName() == null || cardDto.getCardName().equals("")) {
            // field 에러
            errors.rejectValue("cardName", "wrongValue", "cardName 은 필수 값 입니다.");
            // global 에러
            errors.reject("cardName", "Values for cardName are wrong");
        }
    }
}
