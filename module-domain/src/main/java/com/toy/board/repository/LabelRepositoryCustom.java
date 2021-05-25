package com.toy.board.repository;



import com.toy.board.dto.CardLabelQueryDto;

import java.util.List;

public interface LabelRepositoryCustom {

    List<CardLabelQueryDto> findCardLabels(Long cardId);
}
