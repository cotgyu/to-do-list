package com.toy.board.dto;


import com.toy.board.domain.Card;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CardRequestDto {

    private long topicId;
    private String cardName;
    private String description;
    private String delFlag;

    public CardRequestDto(String cardName){
        this.cardName = cardName;
    }

    public CardRequestDto(long topicId, String cardName){
        this.topicId = topicId;
        this.cardName = cardName;
    }

    public CardRequestDto(String cardName, String description, String delFlag) {
        this.cardName = cardName;
        this.description = description;
        this.delFlag = delFlag;
    }

    public Card toEntity(){
        return Card.builder()
                .cardName(cardName)
                .description(description)
                .build();
    }

}
