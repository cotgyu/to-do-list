package com.toy.todolist.board.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

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

    private String delFlag;

    @OneToMany(mappedBy = "checkList", cascade = ALL)
    private List<CheckItem> checkItems = new ArrayList<>();

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "CARD_ID")
    private Card card;

    @Builder
    public CheckList(String checkListName, Card card){
        this.checkListName = checkListName;
        this.card = card;
        this.delFlag = "N";
    }

    public void setCard(Card card){
        this.card = card;
    }

    public void update(String checkListName, String delFlag){
        this.checkListName = checkListName;
        this.delFlag = delFlag;
    }

    public void addCheckItem(CheckItem checkItem){
        checkItems.add(checkItem);
        checkItem.setCheckList(this);
    }
}
