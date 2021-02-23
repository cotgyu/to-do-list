package com.toy.todolist.board.domain;

import lombok.*;

import javax.annotation.Nullable;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of ={"id", "topicName"})
public class Topic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TOPIC_ID")
    private Long id;

    private String topicName;

    @OneToMany(mappedBy = "topic")
    private List<Card> cards = new ArrayList<>();

    public Topic(String topicName){
        this.topicName = topicName;
    }

    @Builder
    public Topic(Long id, String topicName, List<Card> cards){
        this.id = id;
        this.topicName = topicName;
        this.cards = cards;
    }

    public void update(String topicName){
        this.topicName = topicName;
    }
}
