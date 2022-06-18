package com.toy.board.service;

import com.toy.board.domain.Board;
import com.toy.board.domain.Topic;
import com.toy.board.dto.TopicRequestDto;
import com.toy.board.dto.TopicResponseDto;
import com.toy.board.repository.BoardRepository;
import com.toy.board.repository.TopicRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class TopicService {
    private final TopicRepository topicRepository;
    private final BoardRepository boardRepository;

    @Transactional
    public Long save(TopicRequestDto topicRequestDto) {

        long boardId = topicRequestDto.getBoardId();
        Board board = boardRepository.findByIdAndDelFlag(boardId, "N").orElseThrow(() -> new IllegalArgumentException("해당 Board 없습니다. id=" + boardId));

        Topic topic = topicRepository.save(topicRequestDto.toEntity());
        topic.changeBoard(board);

        return topic.getId();
    }

    @Transactional(readOnly = true)
    public TopicResponseDto findTopic(Long id) {
        Topic topic = findById(id);

        return new TopicResponseDto(topic);
    }

    private Topic findById(Long id) {
        return topicRepository.findByIdAndDelFlag(id, "N").orElseThrow(() -> new IllegalArgumentException("해당 Topic이 없습니다. id=" + id));
    }

    @Transactional
    public Long update(Long id, TopicRequestDto topicRequestDto) {
        Topic topic = findById(id);

        topic.update(topicRequestDto.getTopicName(), topicRequestDto.getDelFlag());

        return topic.getId();
    }


}
