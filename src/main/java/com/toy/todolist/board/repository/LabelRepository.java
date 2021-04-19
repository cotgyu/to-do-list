package com.toy.todolist.board.repository;

import com.toy.todolist.board.domain.Label;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LabelRepository extends JpaRepository<Label, Long>, LabelRepositoryCustom {

    List<Label> findAllByDelFlag(String delFlag);
}