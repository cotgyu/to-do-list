package com.toy.todolist.board.repository;

import com.toy.todolist.board.dto.LabelResponseDto;

import java.util.List;

public interface LabelRepositoryCustom {

    List<LabelResponseDto> findCardLabels(Long cardId);
}
