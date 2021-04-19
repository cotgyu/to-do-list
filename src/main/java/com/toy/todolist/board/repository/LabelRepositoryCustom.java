package com.toy.todolist.board.repository;

import com.toy.todolist.board.dto.CardLabelQueryDto;
import com.toy.todolist.board.dto.LabelResponseDto;

import java.util.List;

public interface LabelRepositoryCustom {

    List<CardLabelQueryDto> findCardLabels(Long cardId);
}
