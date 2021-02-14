package com.toy.todolist.board.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of ={"id", "cardName"})
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String cardName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "topic_id")
    private Topic topic;


    public Card(String cardName, Topic topic){
        this.cardName = cardName;
        if(topic != null){
            changeTopic(topic);
        }
    }

    private void changeTopic(Topic topic){
        this.topic = topic;
        topic.getCards().add(this);
    }

}
