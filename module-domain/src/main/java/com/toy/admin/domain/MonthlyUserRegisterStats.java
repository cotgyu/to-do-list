package com.toy.admin.domain;

import com.querydsl.core.annotations.QueryProjection;
import com.toy.common.domain.BaseEntity;
import lombok.Data;
import lombok.Generated;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@NoArgsConstructor
public class MonthlyUserRegisterStats{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int month;
    private Long count;

    public MonthlyUserRegisterStats(int month, Long count){
        this.month = month;
        this.count = count;
    }

    public MonthlyUserRegisterStats(Object[] object){
        this.month = (Integer) object[0];
        this.count = (Long) object[1];
    }


}
