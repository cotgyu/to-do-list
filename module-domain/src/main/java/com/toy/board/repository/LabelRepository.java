package com.toy.board.repository;


import com.toy.board.domain.Label;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LabelRepository extends JpaRepository<Label, Long>, LabelRepositoryCustom {

    List<Label> findAllByDelFlag(String delFlag);

    Optional<Label> findByIdAndDelFlag(long id, String delFlag);
}
