package com.toy.board.repository;

import com.querydsl.core.QueryResults;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.dsl.CaseBuilder;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.toy.board.dto.CardLabelQueryDto;
import com.toy.board.dto.QCardLabelQueryDto;

import javax.persistence.EntityManager;
import java.util.List;

import static com.toy.board.domain.QCardLabel.cardLabel;
import static com.toy.board.domain.QLabel.label;


public class LabelRepositoryImpl implements LabelRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public LabelRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<CardLabelQueryDto> findCardLabels(Long cardId) {

        QueryResults<CardLabelQueryDto> results =
                queryFactory.select(new QCardLabelQueryDto(
                                label.id.as("labelId"),
                                label.labelName,
                                label.color,
                                ExpressionUtils.as(JPAExpressions
                                        .select(new CaseBuilder()
                                                .when(cardLabel.delFlag.eq("N"))
                                                .then("Y")
                                                .otherwise("N"))
                                        .from(cardLabel)
                                        .where(cardLabel.label.id.eq(label.id).and(cardLabel.card.id.eq(cardId))), "checkFlag")
                        ))
                        .from(label)
                        .where(label.delFlag.eq("N"))
                        .fetchResults();


        return results.getResults();
    }
}
