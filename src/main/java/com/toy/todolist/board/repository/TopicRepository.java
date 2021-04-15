package com.toy.todolist.board.repository;

import com.toy.todolist.board.domain.Topic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicRepository extends JpaRepository<Topic, Long> {
}
