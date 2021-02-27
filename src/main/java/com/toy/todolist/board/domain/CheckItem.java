package com.toy.todolist.board.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CheckItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CHECK_ITEM_ID")
    private Long id;

    private String checkItemName;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "CHECK_LIST_ID")
    private CheckList checkList;
}
