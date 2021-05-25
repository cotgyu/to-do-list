package com.toy.user.domain;


import com.toy.user.dto.MonthlyUserRegisterQueryDto;
import com.toy.user.dto.UserBoardStatsQueryDto;

import java.util.List;

public interface UserRepositoryCustom {

    List<MonthlyUserRegisterQueryDto> getMonthlyUserRegisterStatistics(int year);

    List<UserBoardStatsQueryDto> getUserBoardStatistics();

}
