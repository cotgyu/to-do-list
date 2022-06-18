package com.toy.board.dto;


import com.toy.board.domain.Board;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BoardRequestDto {

    private Long id;
    private String boardName;
    private String delFlag;

    public BoardRequestDto(String boardName) {
        this.boardName = boardName;
        this.delFlag = "N";
    }

    public Board toEntity() {
        return Board.builder()
                .boardName(boardName)
                .build();
    }
}
