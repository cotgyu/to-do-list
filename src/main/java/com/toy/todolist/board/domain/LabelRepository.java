package com.toy.todolist.board.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LabelRepository extends JpaRepository<Label, Long>, LabelRepositoryCustom {
}
