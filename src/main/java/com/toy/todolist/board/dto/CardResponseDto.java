package com.toy.todolist.board.dto;

import com.toy.todolist.board.domain.Card;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CardResponseDto {

    private Long id;
    private String cardName;

    public CardResponseDto(Card card){
        this.id = card.getId();
        this.cardName = card.getCardName();
    }

    public Card toEntity(){
        return Card.builder()
                .id(id)
                .cardName(cardName)
                .build();
    }

}
