package com.toy.todolist.board.service;


import com.toy.todolist.board.domain.Board;
import com.toy.todolist.board.domain.BoardRepository;
import com.toy.todolist.board.domain.Topic;
import com.toy.todolist.board.domain.TopicRepository;
import com.toy.todolist.board.dto.BoardRequestDto;
import com.toy.todolist.board.dto.BoardResponseDto;
import com.toy.todolist.board.dto.TopicRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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

        // TODO - 한방 쿼리로 수정 필요
        Board board = boardRepository.findById(boardId).orElseThrow(() -> new IllegalArgumentException("해당 Board가 없습니다. id=" + boardId));

        return new BoardResponseDto(board);
    }


}
