package com.toy.todolist.board.repository;

import com.toy.todolist.board.domain.CheckItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CheckItemRepository extends JpaRepository<CheckItem, Long> {

    Optional<CheckItem> findByIdAndDelFlag(Long id, String delFlag);

}
