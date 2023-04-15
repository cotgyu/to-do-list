package com.toy.study.domain;

public class MemberDto {

    private Long id;

    private String name;

    public MemberDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public MemberDto(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
