package com.toy.todolist.board.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CheckListRepository extends JpaRepository<CheckList, Long> {
}
