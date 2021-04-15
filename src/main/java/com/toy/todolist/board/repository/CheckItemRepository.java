package com.toy.todolist.board.repository;

import com.toy.todolist.board.domain.CheckItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CheckItemRepository extends JpaRepository<CheckItem, Long> {
}
