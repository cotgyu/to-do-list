package com.toy.todolist.board.domain;

import lombok.*;

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

    @Column(length = 1000)
    String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "topic_id")
    private Topic topic;


    public Card(String cardName, Topic topic){
        this.cardName = cardName;
        if(topic != null){
            changeTopic(topic);
        }
    }

    public Card(String cardName, String description, Topic topic){
        this.cardName = cardName;
        this.description = description;
        if(topic != null){
            changeTopic(topic);
        }
    }

    @Builder
    public Card(Long id, String cardName, String description){
        this.id = id;
        this.cardName = cardName;
        this.description = description;
    }

    private void changeTopic(Topic topic){
        this.topic = topic;
        topic.getCards().add(this);
    }

    public void update(String cardName, String description){
        this.cardName = cardName;
        this.description = description;
    }

}
