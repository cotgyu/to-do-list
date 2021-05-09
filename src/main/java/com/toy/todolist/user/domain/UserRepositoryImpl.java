package com.toy.todolist.user.domain;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.toy.todolist.board.domain.QBoard;
import com.toy.todolist.user.dto.MonthlyUserRegisterQueryDto;
import com.toy.todolist.user.dto.QMonthlyUserRegisterQueryDto;
import com.toy.todolist.user.dto.QUserBoardStatsQueryDto;
import com.toy.todolist.user.dto.UserBoardStatsQueryDto;

import javax.persistence.EntityManager;
import java.util.List;

import static com.toy.todolist.board.domain.QBoard.*;
import static com.toy.todolist.user.domain.QUser.user;

public class UserRepositoryImpl implements UserRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public UserRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<MonthlyUserRegisterQueryDto> getMonthlyUserRegisterStatistics(int year) {

        List<MonthlyUserRegisterQueryDto> results =
                queryFactory
                        .select(
                                new QMonthlyUserRegisterQueryDto(
                                        user.createdDate.yearMonth().as("month"),
                                        user.count().as("count")
                                )
                        )
                        .from(user)
                        .where(user.createdDate.year().eq(year))
                        .groupBy(user.createdDate.yearMonth())
                        .fetch();

        return results;
    }

    @Override
    public List<UserBoardStatsQueryDto> getUserBoardStatistics(){

        List<UserBoardStatsQueryDto> results = queryFactory
                .select(
                        new QUserBoardStatsQueryDto(
                                board.id.count(),
                                user.id,
                                user.name
                        )
                )
                .from(board)
                .leftJoin(board.user, user)
                .where(user.delFlag.eq("N"))
                .groupBy(user.id)
                .orderBy(board.id.count().desc())
                .limit(10)
                .fetch();

        return results;
    }

}
