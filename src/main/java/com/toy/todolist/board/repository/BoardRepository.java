package com.toy.todolist.board.repository;

import com.toy.todolist.board.domain.Board;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long>, BoardRepositoryCustom {

    List<Board> findBoardByUserIdAndDelFlag(long userId, String deFlag);

}
