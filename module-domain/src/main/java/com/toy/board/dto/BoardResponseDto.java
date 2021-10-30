package com.toy.board.dto;

import com.toy.board.domain.Board;
import com.toy.board.domain.Topic;
import com.toy.user.dto.UserResponseDto;
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
    private UserResponseDto user;

    public BoardResponseDto(Board board){
        id = board.getId();
        boardName = board.getBoardName();
        delFlag = board.getDelFlag();
        user = new UserResponseDto(board.getUser());
    }

    public BoardResponseDto(Board board, List<Topic> topicList){
        id = board.getId();
        boardName = board.getBoardName();
        delFlag = board.getDelFlag();

        topics = topicList
                .stream()
                .filter(topic -> topic.getDelFlag().equals("N"))
                .map(topic -> new TopicResponseDto(topic))
                .collect(Collectors.toList());

        user = new UserResponseDto(board.getUser());
    }

}
