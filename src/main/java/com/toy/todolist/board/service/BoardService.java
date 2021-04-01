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
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class BoardService {

    private final TopicRepository topicRepository;

    private final BoardRepository boardRepository;

    @Transactional
    public Long save(BoardRequestDto boardRequestDto){
        Board board = boardRepository.save(boardRequestDto.toEntity());

        return board.getId();
    }

    @Transactional
    public Long updateBoard(long boardId, BoardRequestDto boardRequestDto){

        Board board = findBoardById(boardId);

        board.update(boardRequestDto.getBoardName(), boardRequestDto.getDelFlag());

        return board.getId();
    }

    @Transactional(readOnly = true)
    public BoardResponseDto findAllContents(Long boardId){

        // TODO - 한방 쿼리로 수정 필요 (delflag 처리포함)
        Board board = findBoardById(boardId);

        BoardResponseDto boardResponseDto = new BoardResponseDto(board);

        List<TopicResponseDto> topics = boardResponseDto.getTopics();

        List<TopicResponseDto> returnList = topics.stream()
                .filter(topicResponseDto -> topicResponseDto.getDelFlag() == null || topicResponseDto.getDelFlag().equals("N"))
                .collect(Collectors.toList());

        boardResponseDto.setTopics(returnList);

        return boardResponseDto;
    }

    private Board findBoardById(Long boardId) {
        Board board = boardRepository.findById(boardId).orElseThrow(() -> new IllegalArgumentException("해당 Board가 없습니다. id=" + boardId));
        return board;
    }

    @Transactional(readOnly = true)
    public List<BoardResponseDto> findAllBoardList(){
        List<Board> all = boardRepository.findAll();

        List<BoardResponseDto> boardResponseDtoList = all.stream()
                .filter(board -> board.getDelFlag() == null || board.getDelFlag().equals("N"))
                .map(board -> new BoardResponseDto(board))
                .collect(Collectors.toList());

        return boardResponseDtoList;
    }


}
