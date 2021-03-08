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

    private String checkFlag;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "CHECK_LIST_ID")
    private CheckList checkList;

    @Builder
    public CheckItem(String checkItemName, CheckList checkList){
        this.checkItemName = checkItemName;
        this.checkList = checkList;
        this.delFlag = "N";
        this.checkFlag = "N";
    }

    public void setCheckList(CheckList checkList){
        this.checkList = checkList;
    }

    public void update(String checkItemName, String delFlag, String checkFlag){
        this.checkItemName = checkItemName;
        this.delFlag = delFlag;
        this.checkFlag = checkFlag;
    }
}
