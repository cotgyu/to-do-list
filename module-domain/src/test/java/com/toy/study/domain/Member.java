package com.toy.study.domain;

import org.springframework.data.annotation.Id;

public class Member {

    @Id
    private Long id;

    private String name;

    public Member(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
