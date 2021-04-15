package com.toy.todolist.board.domain;

import com.toy.todolist.user.domain.User;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of ={"id", "boardName"})
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BOARD_ID")
    private Long id;

    private String boardName;

    private String delFlag;

    @OneToMany(mappedBy = "board")
    private List<Topic> topics = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public Board(String boardName){
        this.boardName = boardName;
        this.delFlag = "N";
    }

    @Builder
    public Board(String boardName, List<Topic> topics, User user){
        this.boardName = boardName;
        this.topics = topics;
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
