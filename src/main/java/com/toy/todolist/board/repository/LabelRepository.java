package com.toy.todolist.board.repository;

import com.toy.todolist.board.domain.Label;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LabelRepository extends JpaRepository<Label, Long>, LabelRepositoryCustom {
}
