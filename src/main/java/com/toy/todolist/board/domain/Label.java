package com.toy.todolist.board.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Label {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "LABEL_ID")
    private Long id;

    private String labelName;

    private String color;

    @Builder
    public Label(Long id, String labelName, String color){
        this.id = id;
        this.labelName = labelName;
        this.color = color;
    }



}
