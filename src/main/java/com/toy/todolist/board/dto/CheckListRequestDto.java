package com.toy.todolist.board.dto;


import com.toy.todolist.board.domain.CheckList;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CheckListRequestDto {

    String checkListTitle;
    Long cardId;
    String delFlag;

    public CheckListRequestDto(String checkListTitle, Long cardId){
        this.checkListTitle = checkListTitle;
        this.cardId = cardId;
    }

    public CheckListRequestDto(String checkListTitle, String delFlag){
        this.checkListTitle = checkListTitle;
        this.delFlag = delFlag;
    }

    public CheckList toEntity(){
        return CheckList
                .builder()
                .checkListName(checkListTitle)
                .build();
    }
}
