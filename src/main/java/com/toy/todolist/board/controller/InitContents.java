package com.toy.todolist.board.controller;

import com.toy.todolist.board.domain.*;
import com.toy.todolist.user.domain.Role;
import com.toy.todolist.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Component
@RequiredArgsConstructor
public class InitContents {

    private final InitContentsService initContentsService;

    @PostConstruct
    public void init(){
        initContentsService.init();
    }

    @Component
    static class InitContentsService{

        @PersistenceContext
        private EntityManager em;

        @Transactional
        public void init(){

            User testUser = new User("testUser", "v123v123s@gmail.com", "https://lh6.googleusercontent.com/-XRdI0_dL6cQ/AAAAAAAAAAI/AAAAAAAAAAA/AMZuucm6Uc0lgVjtVlZQFcP1U69RETkOfA/s96-c/photo.jpg", Role.ADMIN);
            User testUser2 = new User("testUser2", "v123v123s2@gmail.com", "2.jpg", Role.USER);
            User testUser3 = new User("testUser3", "v123v123s3@gmail.com", "3.jpg", Role.USER);
            User testUser4 = new User("testUser4", "v123v123s4@gmail.com", "4.jpg", Role.USER);
            User testUser5 = new User("testUser5", "v123v123s5@gmail.com", "5.jpg", Role.USER);

            em.persist(testUser);
            em.persist(testUser2);
            em.persist(testUser3);
            em.persist(testUser4);
            em.persist(testUser5);

            //given
            Board board = new Board("board1");
            Board board2 = new Board("board2");
            Board board3 = new Board("board3");

            board.changeUser(testUser);
            board2.changeUser(testUser);
            board3.changeUser(testUser);

            em.persist(board);
            em.persist(board2);
            em.persist(board3);

            Topic topic1 = new Topic("topic1", board);
            Topic topic2 = new Topic("topic2", board);
            em.persist(topic1);
            em.persist(topic2);


            Card card1 = new Card("card1", "des1", topic1);
            Card card2 = new Card("card2", "des2", topic1);
            Card card3 = new Card("card3", "des3", topic2);
            Card card4 = new Card("card4", "des4", topic2);
            Card card5 = new Card("card5", "des5", topic2);
            Card card6 = new Card("card6", "des6", topic2);
            em.persist(card1);
            em.persist(card2);
            em.persist(card3);
            em.persist(card4);
            em.persist(card5);
            em.persist(card6);

            CheckList checkList1 = new CheckList("checkList1", card1);
            CheckList checkList2 = new CheckList("checkList2", card1);
            em.persist(checkList1);
            em.persist(checkList2);

            CheckItem checkItem1 = new CheckItem("checkItem1",checkList1);
            CheckItem checkItem2 = new CheckItem("checkItem2",checkList1);
            CheckItem checkItem3 = new CheckItem("checkItem3",checkList1);
            CheckItem checkItem4 = new CheckItem("checkItem4",checkList2);
            CheckItem checkItem5 = new CheckItem("checkItem5",checkList2);

            checkItem1.update("checkItem1", "N", "Y");
            checkItem2.update("checkItem2", "N", "Y");

            em.persist(checkItem1);
            em.persist(checkItem2);
            em.persist(checkItem3);
            em.persist(checkItem4);
            em.persist(checkItem5);

            checkList1.addCheckItem(checkItem1);
            checkList1.addCheckItem(checkItem2);
            checkList1.addCheckItem(checkItem3);
            checkList2.addCheckItem(checkItem4);
            checkList2.addCheckItem(checkItem5);

            Label label1 = new Label("label1", "green");
            Label label2 = new Label("label2", "red");
            Label label3 = new Label("label3", "blue");
            Label label4 = new Label("label4", "yellow");

            em.persist(label1);
            em.persist(label2);
            em.persist(label3);
            em.persist(label4);

            CardLabel cardLabel1 = new CardLabel(card1, label1);
            CardLabel cardLabel2 = new CardLabel(card2, label2);
            CardLabel cardLabel3 = new CardLabel(card1, label3);
            CardLabel cardLabel4 = new CardLabel(card4, label3);
            CardLabel cardLabel5 = new CardLabel(card2, label3);


            card1.addCardLabel(cardLabel1);
            card2.addCardLabel(cardLabel2);
            card2.addCardLabel(cardLabel5);
            card1.addCardLabel(cardLabel3);
            card4.addCardLabel(cardLabel4);



        }

    }
}
