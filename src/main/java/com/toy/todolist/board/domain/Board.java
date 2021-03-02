package com.toy.todolist.board.domain;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BOARD_ID")
    private Long id;

    private String boardName;

    private String delFlag;

}
