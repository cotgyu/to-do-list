package com.toy.todolist.user.domain;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.toy.todolist.user.dto.MonthlyUserRegisterQueryDto;
import com.toy.todolist.user.dto.QMonthlyUserRegisterQueryDto;

import javax.persistence.EntityManager;
import java.util.List;

import static com.toy.todolist.user.domain.QUser.user;

public class UserRepositoryImpl implements UserRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public UserRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<MonthlyUserRegisterQueryDto> getMonthlyUserRegisterStatistics() {

        List<MonthlyUserRegisterQueryDto> results =
                queryFactory
                        .select(
                                new QMonthlyUserRegisterQueryDto(
                                        user.createdDate.yearMonth().as("month"),
                                        user.count().as("count")
                                )
                        )
                        .from(user)
                        .groupBy(user.createdDate.yearMonth())
                        .fetch();

        return results;
    }

}
