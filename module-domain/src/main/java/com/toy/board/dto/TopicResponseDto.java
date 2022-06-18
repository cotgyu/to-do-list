package com.toy.board.dto;


import com.toy.board.domain.Topic;
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
    private String delFlag;

    public TopicResponseDto(Topic topic) {
        id = topic.getId();
        topicName = topic.getTopicName();
        cards = topic.getCards()
                .stream()
                .filter(card -> card.getDelFlag().equals("N"))
                .map(card -> new CardResponseDto(card))
                .collect(Collectors.toList());
        delFlag = topic.getDelFlag();
    }


}
