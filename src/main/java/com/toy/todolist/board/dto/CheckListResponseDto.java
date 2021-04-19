package com.toy.todolist.board.dto;


import com.toy.todolist.board.domain.CheckList;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CheckListResponseDto {

    private Long id;
    private String checkListName;
    private Long cardId;
    private String delFlag;
    private List<CheckItemResponseDto> checkItems = new ArrayList<>();
    private Long totalCount;
    private Long checkedCount;

    public CheckListResponseDto(CheckList checkList){
        id = checkList.getId();
        checkListName = checkList.getCheckListName();
        cardId = checkList.getCard().getId();
        delFlag = checkList.getDelFlag();
        checkItems = checkList.getCheckItems()
                .stream()
                .filter(checkItem -> checkItem.getDelFlag().equals("N"))
                .map(checkItem -> new CheckItemResponseDto(checkItem))
                .collect(Collectors.toList());

        totalCount = checkList.getCheckItems()
                .stream()
                .filter(checkItem -> checkItem.getDelFlag().equals("N"))
                .count();

        checkedCount = checkList.getCheckItems()
                .stream()
                .filter(checkItem -> checkItem.getDelFlag().equals("N"))
                .filter(checkItem -> checkItem.getCheckFlag().equals("Y"))
                .count();
    }

}
