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

    @OneToMany(mappedBy = "card", cascade = ALL)
    private List<CheckList> checkLists = new ArrayList<>();

    private String delFlag;

    public Card(String cardName, Topic topic){
        this.cardName = cardName;
        if(topic != null){
            changeTopic(topic);
        }
        this.delFlag = "N";
    }

    public Card(String cardName, String description, Topic topic){
        this.cardName = cardName;
        this.description = description;
        if(topic != null){
            changeTopic(topic);
        }
        this.delFlag = "N";
    }

    @Builder
    public Card(Long id, String cardName, String description){
        this.id = id;
        this.cardName = cardName;
        this.description = description;
        this.delFlag = "N";
    }

    public void changeTopic(Topic topic){
        this.topic = topic;
        topic.getCards().add(this);
    }

    public void addCardLabel(CardLabel cardLabel){
        cardLabels.add(cardLabel);
        cardLabel.setCard(this);
    }

    public void addCheckList(CheckList checkList){
        checkLists.add(checkList);
        checkList.setCard(this);
    }

    public void update(String cardName, String description, String delFlag){
        this.cardName = cardName;
        this.description = description;
        this.delFlag = delFlag;
    }

}
