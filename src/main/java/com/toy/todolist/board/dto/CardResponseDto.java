package com.toy.todolist.board.dto;

import com.toy.todolist.board.domain.Card;
import com.toy.todolist.board.domain.CardLabel;
import com.toy.todolist.board.domain.CheckList;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CardResponseDto {

    private Long id;
    private String cardName;
    private String description;
    private List<CardLabelResponseDto> cardLabels = new ArrayList<>();
    private List<CheckListResponseDto> checkLists = new ArrayList<>();

    private String delFlag;

    public CardResponseDto(Card card){
        this.id = card.getId();
        this.cardName = card.getCardName();
        this.description = card.getDescription();
        this.cardLabels = card.getCardLabels()
                .stream()
                .filter(cardLabel -> cardLabel.getDelFlag().equals("N"))
                .map(cardLabel -> new CardLabelResponseDto(cardLabel))
                .collect(Collectors.toList());

        this.checkLists = card.getCheckLists()
                .stream()
                .filter(checkList -> checkList.getDelFlag().equals("N"))
                .map(checkList -> new CheckListResponseDto(checkList))
                .collect(Collectors.toList());

    }

}
