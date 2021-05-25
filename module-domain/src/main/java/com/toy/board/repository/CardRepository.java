package com.toy.board.repository;


import com.toy.board.domain.Card;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CardRepository extends JpaRepository<Card, Long> {

    Optional<Card> findByIdAndDelFlag(Long id, String delFlag);
}
