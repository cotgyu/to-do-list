package com.toy.board.repository;


import com.toy.board.domain.Topic;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TopicRepository extends JpaRepository<Topic, Long> {
    Optional<Topic> findByIdAndDelFlag(long id, String delFlag);
    List<Topic> findAllByBoardIdAndDelFlag(long boardId, String delFlag);
}
