package com.toy.todolist.board.repository;

import com.toy.todolist.board.domain.Topic;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TopicRepository extends JpaRepository<Topic, Long> {

    Optional<Topic> findByIdAndDelFlag(long id, String delFlag);
}
