package com.toy.todolist.board.dto;


import com.toy.todolist.board.domain.CheckItem;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CheckItemResponseDto {

    private Long checkItemId;
    private String checkItemName;
    private Long checkListId;
    private String delFlag;
    private String checkFlag;

    public CheckItemResponseDto(CheckItem checkItem){
        checkItemId = checkItem.getId();
        checkItemName = checkItem.getCheckItemName();
        checkListId = checkItem.getCheckList().getId();
        delFlag = checkItem.getDelFlag();
        checkFlag = checkItem.getCheckFlag();
    }

}
