package com.toy.board.service;



import com.toy.board.domain.Board;
import com.toy.board.dto.BoardRequestDto;
import com.toy.board.dto.BoardResponseDto;
import com.toy.board.dto.TopicResponseDto;
import com.toy.board.repository.BoardRepository;
import com.toy.board.repository.TopicRepository;
import com.toy.config.auth.dto.SessionUser;
import com.toy.user.domain.User;
import com.toy.user.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class BoardService {

    private final TopicRepository topicRepository;

    private final BoardRepository boardRepository;

    private final UserRepository userRepository;

    @Transactional
    public Long save(BoardRequestDto boardRequestDto, String email){

        User user = userRepository.findByEmail(email).orElseThrow((() -> new IllegalArgumentException("해당 User가 없습니다. email=" + email)));

        Board board = boardRequestDto.toEntity();
        board.changeUser(user);

        Board saveBoard = boardRepository.save(board);

        return saveBoard.getId();
    }

    @Transactional
    public Long updateBoard(long boardId, BoardRequestDto boardRequestDto, SessionUser user){

        Board board = findBoardById(boardId);

        if(board.getUser().getId() != user.getId()){
            return -1L;
        }

        board.update(boardRequestDto.getBoardName(), boardRequestDto.getDelFlag());

        return board.getId();
    }

    @Transactional(readOnly = true)
    public BoardResponseDto findAllContents(Long boardId){

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
        Board board = boardRepository.findByIdAndDelFlag(boardId, "N").orElseThrow(() -> new IllegalArgumentException("해당 Board가 없습니다. id=" + boardId));
        return board;
    }

    @Transactional(readOnly = true)
    public List<BoardResponseDto> findAllBoardListByEmail(String email){

        User user = userRepository.findByEmail(email).orElseThrow((() -> new IllegalArgumentException("해당 User가 없습니다. email=" + email)));

        List<Board> allBoard = boardRepository.findBoardByUserIdAndDelFlag(user.getId(), "N");

        List<BoardResponseDto> boardResponseDtoList = allBoard.stream()
                .map(board -> new BoardResponseDto(board))
                .collect(Collectors.toList());

        return boardResponseDtoList;
    }


}
