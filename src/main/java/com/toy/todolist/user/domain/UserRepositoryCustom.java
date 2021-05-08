package com.toy.todolist.user.domain;

import com.toy.todolist.user.dto.MonthlyUserRegisterQueryDto;
import com.toy.todolist.user.dto.UserBoardStatsQueryDto;

import java.util.List;

public interface UserRepositoryCustom {

    List<MonthlyUserRegisterQueryDto> getMonthlyUserRegisterStatistics(int year);

    List<UserBoardStatsQueryDto> getUserBoardStatistics();

}
