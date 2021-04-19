package com.toy.todolist.board.dto;

import com.toy.todolist.board.domain.Board;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BoardResponseDto {
    private Long id;
    private String boardName;
    private String delFlag;
    private List<TopicResponseDto> topics = new ArrayList<>();

    public BoardResponseDto(Board board){
        id = board.getId();
        boardName = board.getBoardName();
        delFlag = board.getDelFlag();

        topics = board.getTopics()
                .stream()
                .filter(topic -> topic.getDelFlag().equals("N"))
                .map(topic -> new TopicResponseDto(topic))
                .collect(Collectors.toList());
    }

}
