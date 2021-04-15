package com.toy.todolist.board.repository;

import com.toy.todolist.board.domain.CheckList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CheckListRepository extends JpaRepository<CheckList, Long> {
}
