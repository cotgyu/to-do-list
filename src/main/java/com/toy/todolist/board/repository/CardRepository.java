package com.toy.todolist.board.repository;

import com.toy.todolist.board.domain.Card;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepository extends JpaRepository<Card, Long> {
}
