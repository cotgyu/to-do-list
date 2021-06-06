package com.toy.user.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
@Getter
@Setter
@NoArgsConstructor
public class UserBoardStats {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long count;
    private Long userId;
    private String name;

    public UserBoardStats(Long count, Long userId, String name) {
        this.count = count;
        this.userId = userId;
        this.name = name;
    }
}
