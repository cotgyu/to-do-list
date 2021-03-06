package com.toy.board.repository;


import com.toy.board.domain.CheckList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CheckListRepository extends JpaRepository<CheckList, Long> {

    Optional<CheckList> findByIdAndDelFlag(Long id, String delFlag);
}
