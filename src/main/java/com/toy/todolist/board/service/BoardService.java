package com.toy.todolist.board.service;


import com.toy.todolist.board.domain.Board;
import com.toy.todolist.board.domain.BoardRepository;
import com.toy.todolist.board.domain.Topic;
import com.toy.todolist.board.domain.TopicRepository;
import com.toy.todolist.board.dto.BoardRequestDto;
import com.toy.todolist.board.dto.BoardResponseDto;
import com.toy.todolist.board.dto.TopicRequestDto;
import com.toy.todolist.board.dto.TopicResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class BoardService {

    private final TopicRepository topicRepository;

    private final BoardRepository boardRepository;

    public Long save(BoardRequestDto boardRequestDto){
        Board board = boardRepository.save(boardRequestDto.toEntity());

        return board.getId();
    }

    public BoardResponseDto findAllContents(Long boardId){

        // TODO - 한방 쿼리로 수정 필요 (delflag 처리포함)
        Board board = boardRepository.findById(boardId).orElseThrow(() -> new IllegalArgumentException("해당 Board가 없습니다. id=" + boardId));

        BoardResponseDto boardResponseDto = new BoardResponseDto(board);

        List<TopicResponseDto> returnList = new ArrayList<>();
        List<TopicResponseDto> topics = boardResponseDto.getTopics();

        for (TopicResponseDto topic : topics) {
            if(topic.getDelFlag()== null || topic.getDelFlag().equals("N"))
                returnList.add(topic);
        }

        boardResponseDto.setTopics(returnList);

        return boardResponseDto;
    }


}
