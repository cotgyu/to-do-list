package com.toy.todolist.board.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.toy.todolist.board.dto.LabelResponseDto;

import javax.persistence.EntityManager;
import java.util.List;

public class LabelRepositoryImpl implements LabelRepositoryCustom{

    private final JPAQueryFactory queryFactory;

    public LabelRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<LabelResponseDto> findCardLabels(Long cardId) {



        return null;
    }
}
