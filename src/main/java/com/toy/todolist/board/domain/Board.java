package com.toy.todolist.board.domain;

import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BOARD_ID")
    private Long id;

    private String boardName;

    private String delFlag;

    @OneToMany(mappedBy = "board")
    private List<Topic> topics = new ArrayList<>();

    @Builder
    public Board(String boardName, List<Topic> topics){
        this.boardName = boardName;
        this.topics = topics;
        this.delFlag = "N";
    }

}
