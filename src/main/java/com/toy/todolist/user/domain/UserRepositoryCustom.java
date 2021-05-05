package com.toy.todolist.user.domain;

import com.toy.todolist.user.dto.MonthlyUserRegisterQueryDto;

import java.util.List;

public interface UserRepositoryCustom {

    List<MonthlyUserRegisterQueryDto> getMonthlyUserRegisterStatistics();

}
