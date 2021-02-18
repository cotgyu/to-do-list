package com.toy.todolist.board.dto;

import com.toy.todolist.board.domain.Card;
import com.toy.todolist.board.domain.Topic;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CardRequestDto {

    private String cardName;

    public CardRequestDto(String cardName){
        this.cardName = cardName;
    }


    public Card toEntity(){
        return Card.builder().
                cardName(cardName)
                .build();
    }

}
