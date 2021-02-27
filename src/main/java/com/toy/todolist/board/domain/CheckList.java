package com.toy.todolist.board.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CheckList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CHECK_LIST_ID")
    private Long id;

    private String checkListName;

    @OneToMany(mappedBy = "checkList", cascade = ALL)
    private List<CheckItem> checkItems = new ArrayList<>();

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "CARD_ID")
    private Card card;
}
