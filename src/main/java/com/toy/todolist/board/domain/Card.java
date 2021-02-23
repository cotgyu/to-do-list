package com.toy.todolist.board.domain;

import lombok.*;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

import static javax.persistence.CascadeType.ALL;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of ={"id", "cardName"})
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CARD_ID")
    private Long id;

    private String cardName;

    @Column(length = 1000)
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "topic_id")
    private Topic topic;

    @OneToMany(mappedBy = "card", cascade = ALL)
    private List<CardLabel> cardLabels = new ArrayList<>();

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

    public void addCardLabel(CardLabel cardLabel){
        cardLabels.add(cardLabel);
        cardLabel.setCard(this);
    }

    public void update(String cardName, String description){
        this.cardName = cardName;
        this.description = description;
    }

}
