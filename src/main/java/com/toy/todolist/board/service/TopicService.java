package com.toy.todolist.board.service;

import com.toy.todolist.board.domain.Topic;
import com.toy.todolist.board.domain.TopicRepository;
import com.toy.todolist.board.dto.TopicRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class TopicService {
    private final TopicRepository topicRepository;

    @Transactional
    public Long save(TopicRequestDto topicRequestDto){
        Topic topic = topicRepository.save(topicRequestDto.toEntity());

        return topic.getId();
    }
}
