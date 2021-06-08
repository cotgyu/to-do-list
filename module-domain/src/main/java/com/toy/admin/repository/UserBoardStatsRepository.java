package com.toy.admin.repository;

import com.toy.admin.domain.UserBoardStats;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserBoardStatsRepository extends JpaRepository<UserBoardStats, Long> {
}
