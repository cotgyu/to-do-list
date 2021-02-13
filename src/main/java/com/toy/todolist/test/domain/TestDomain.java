package com.toy.todolist.test.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class TestDomain {

    @Id @GeneratedValue
    private long id;

    private String name;

    public TestDomain(String name){
        this.name = name;
    }
}
