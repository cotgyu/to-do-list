package com.toy.todolist.board.dto;

import com.toy.todolist.board.domain.Card;
import com.toy.todolist.board.domain.Topic;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TopicRequestDto {

    private Long id;
    private String topicName;

    public TopicRequestDto(String topicName){
        this.topicName = topicName;
    }

    public Topic toEntity() {
        return Topic.builder()
                .id(id)
                .topicName(topicName)
                .build();
    }


}
