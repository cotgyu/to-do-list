package com.toy.board.repository;

import com.toy.board.domain.Board;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BoardRepository extends JpaRepository<Board, Long> {

    List<Board> findBoardByUserIdAndDelFlag(Long userId, String deFlag);

    Optional<Board> findByIdAndDelFlag(Long boardId, String delFlag);

}
