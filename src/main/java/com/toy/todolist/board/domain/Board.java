package com.toy.todolist.board.domain;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of ={"id", "boardName"})
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BOARD_ID")
    private Long id;

    private String boardName;

    private String delFlag;

    @OneToMany(mappedBy = "board")
    private List<Topic> topics = new ArrayList<>();

    public Board(String boardName){
        this.boardName = boardName;
    }

    @Builder
    public Board(String boardName, List<Topic> topics){
        this.boardName = boardName;
        this.topics = topics;
        this.delFlag = "N";
    }


}
