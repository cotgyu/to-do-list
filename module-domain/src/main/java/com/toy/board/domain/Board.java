package com.toy.board.domain;

import com.toy.common.domain.BaseEntity;
import com.toy.user.domain.User;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of ={"id", "boardName"})
public class Board extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BOARD_ID")
    private Long id;

    private String boardName;

    private String delFlag;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public Board(String boardName){
        this.boardName = boardName;
        this.delFlag = "N";
    }

    @Builder
    public Board(String boardName, User user){
        this.boardName = boardName;
        this.user = user;
        this.delFlag = "N";
    }

    public void update(String boardName, String delFlag){
        this.boardName = boardName;
        this.delFlag = delFlag;
    }

    public void changeUser(User user){
        this.user = user;
        user.getBoards().add(this);
    }

}
