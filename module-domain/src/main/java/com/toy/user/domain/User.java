package com.toy.user.domain;

import com.toy.board.domain.Board;
import com.toy.common.domain.BaseEntity;
import com.toy.user.dto.UserRequestDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.CascadeType.ALL;

@Getter
@NoArgsConstructor
@Entity
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID")
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column
    private String picture;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @OneToMany(mappedBy = "user", cascade = ALL)
    private List<Board> boards = new ArrayList<>();

    private String delFlag;

    private LocalDateTime lastAccessTime;

    @Builder
    public User(long id, String name, String email, String picture, Role role) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.picture = picture;
        this.role = role;
        this.delFlag = "N";
    }

    public User(String name, String email, String picture, Role role) {
        this.name = name;
        this.email = email;
        this.picture = picture;
        this.role = role;
        this.delFlag = "N";
    }

    public User update(String name, String picture) {
        this.name = name;
        this.picture = picture;

        return this;
    }

    public User update(String name, String picture, String email, String delFlag) {
        this.name = name;
        this.picture = picture;
        this.email = email;
        this.delFlag = delFlag;

        return this;
    }

    public User update(UserRequestDto userRequestDto) {
        this.name = userRequestDto.getName();
        this.picture = userRequestDto.getPicture();
        this.email = userRequestDto.getEmail();
        this.delFlag = userRequestDto.getDelFlag();
        this.role = userRequestDto.getRole();

        return this;
    }

    public User updateLastAccessTime(LocalDateTime lastAccessTime) {
        this.lastAccessTime = lastAccessTime;

        return this;
    }

    public String getRoleKey() {
        return this.role.getKey();
    }

}
