package com.toy.admin.repository;

import com.toy.admin.domain.MonthlyUserRegisterStats;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MonthlyUserRegisterStatsRepository extends JpaRepository<MonthlyUserRegisterStats, Long> {
}
