package com.toy.todolist.board.domain;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.toy.todolist.board.dto.LabelResponseDto;
import com.toy.todolist.board.dto.QCardLabelQueryDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import javax.persistence.EntityManager;
import java.util.List;

import static com.toy.todolist.board.domain.QCard.card;

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
