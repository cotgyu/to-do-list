package com.toy.user.repository;


import com.toy.admin.dto.MonthlyUserRegisterQueryDto;
import com.toy.admin.dto.UserBoardStatsQueryDto;

import java.util.List;

public interface UserRepositoryCustom {

    List<MonthlyUserRegisterQueryDto> getMonthlyUserRegisterStatistics(int year);

    List<UserBoardStatsQueryDto> getUserBoardStatistics();

}
