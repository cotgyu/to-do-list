package com.toy.todolist.board.domain;

import lombok.AccessLevel;
import lombok.Builder;
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

    private String delFlag;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "CHECK_LIST_ID")
    private CheckList checkList;

    @Builder
    public CheckItem(String checkItemName, CheckList checkList){
        this.checkItemName = checkItemName;
        this.checkList = checkList;
        this.delFlag = "N";
    }

    public void setCheckList(CheckList checkList){
        this.checkList = checkList;
    }
}
