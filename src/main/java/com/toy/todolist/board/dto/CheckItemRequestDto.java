package com.toy.todolist.board.dto;


import com.toy.todolist.board.domain.CheckItem;
import com.toy.todolist.board.domain.CheckList;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CheckItemRequestDto {

    String checkItemName;
    Long checkListId;
    String delFlag;

    public CheckItemRequestDto(String checkListTitle, Long checkListId){
        this.checkItemName = checkListTitle;
        this.checkListId = checkListId;
    }

    public CheckItemRequestDto(String checkListTitle, String delFlag){
        this.checkItemName = checkListTitle;
        this.delFlag = delFlag;
    }

    public CheckItem toEntity(){
        return CheckItem
                .builder()
                .checkItemName(checkItemName)
                .build();
    }
}
