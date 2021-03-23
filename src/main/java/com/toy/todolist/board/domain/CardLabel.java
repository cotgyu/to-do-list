package com.toy.todolist.board.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CardLabel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CARD_LABEL_ID")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "CARD_ID")
    private Card card;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "LABEL_ID")
    private Label label;

    private String delFlag;

    public void setCard(Card card){
        this.card = card;
    }

    @Builder
    public CardLabel(Card card, Label label){
        this.card = card;
        this.label = label;
        this.delFlag = "N";
    }

    public void update(String delFlag){
        this.delFlag = delFlag;
    }

}
