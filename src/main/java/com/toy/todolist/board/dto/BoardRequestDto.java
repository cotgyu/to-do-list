package com.toy.todolist.board.dto;

import com.toy.todolist.board.domain.Board;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BoardRequestDto {

    private Long id;
    private String boardName;

    public BoardRequestDto(String boardName){
     this.boardName = boardName;
    }

    public Board toEntity(){
        return Board.builder()
                .boardName(boardName)
                .build();
    }
}
