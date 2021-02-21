package com.toy.todolist.board.service;

import com.toy.todolist.board.domain.Topic;
import com.toy.todolist.board.domain.TopicRepository;
import com.toy.todolist.board.dto.TopicRequestDto;
import com.toy.todolist.board.dto.TopicResponseDto;
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

    @Transactional(readOnly = true)
    public TopicResponseDto findTopic(Long id){
        Topic topic = findById(id);

        return new TopicResponseDto(topic);
    }

    private Topic findById(Long id) {
        Topic topic = topicRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 Topic이 없습니다. id=" + id));
        return topic;
    }

    @Transactional
    public Long update(Long id, TopicRequestDto topicRequestDto){
        Topic topic = findById(id);

        topic.update(topicRequestDto.getTopicName());

        return topic.getId();
    }


}
