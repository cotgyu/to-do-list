package com.toy.board.dto;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import java.time.LocalDateTime;

@Component
public class BoardValidator {

    public void validate(BoardRequestDto boardDto, Errors errors){

        if(boardDto.getBoardName() == null || boardDto.getBoardName().equals("")){

            // field 에러
            errors.rejectValue("boardName", "wrongValue","boardName 은 필수 값 입니다.");

            // global 에러
            errors.reject("boardName", "Values for boardName are wrong");
        }
    }
}
