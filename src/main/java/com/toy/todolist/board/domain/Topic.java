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

    private String delFlag;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board board;

    public Topic(String topicName){
        this.topicName = topicName;
    }

    @Builder
    public Topic(Long id, String topicName, List<Card> cards){
        this.id = id;
        this.topicName = topicName;
        this.cards = cards;
        this.delFlag = "N";
    }

    public Topic(String topicName, Board board) {
        this.topicName = topicName;
        if(board != null){
            changeBoard(board);
        }
    }

    public void update(String topicName, String delFlag){
        this.topicName = topicName;
        this.delFlag = delFlag;
    }

    public void changeBoard(Board board){
        this.board = board;
        board.getTopics().add(this);
    }
}
