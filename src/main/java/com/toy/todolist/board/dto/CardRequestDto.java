package com.toy.todolist.board.dto;

import com.toy.todolist.board.domain.Card;
import com.toy.todolist.board.domain.Topic;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CardRequestDto {

    private String cardName;
    private String description;
    private String delFlag;

    public CardRequestDto(String cardName){
        this.cardName = cardName;
    }


    public Card toEntity(){
        return Card.builder()
                .cardName(cardName)
                .description(description)
                .build();
    }

}
