package com.toy.todolist.board.dto;

import com.toy.todolist.board.domain.Topic;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TopicResponseDto {

    private Long id;
    private String topicName;
    private List<CardResponseDto> cards;

    public TopicResponseDto(Topic topic){
        id = topic.getId();
        topicName = topic.getTopicName();
        cards = topic.getCards().stream()
            .map(cards -> new CardResponseDto(cards))
            .collect(Collectors.toList());
    }


}
