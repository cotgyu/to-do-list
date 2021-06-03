package com.toy.board.domain;

import com.toy.common.domain.BaseEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Label extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "LABEL_ID")
    private Long id;

    private String labelName;

    private String color;

    private String delFlag;

    public Label(String labelName, String color){
        this.labelName = labelName;
        this.color = color;
        this.delFlag = "N";
    }

    public void update(String labelName, String color, String delFlag){
        this.labelName = labelName;
        this.color = color;
        this.delFlag = delFlag;
    }

    @Builder
    public Label(Long id, String labelName, String color){
        this.id = id;
        this.labelName = labelName;
        this.color = color;
        this.delFlag = "N";
    }



}
