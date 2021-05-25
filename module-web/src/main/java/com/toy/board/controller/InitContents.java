package com.toy.board.controller;

import com.toy.board.domain.*;
import com.toy.user.domain.Role;
import com.toy.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;

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
            User testUser2 = new User("testUser2", "testUser2@gmail.com", "2.jpg", Role.USER);
            User testUser3 = new User("testUser3", "testUser3@gmail.com", "3.jpg", Role.USER);
            User testUser4 = new User("testUser4", "testUser4@gmail.com", "4.jpg", Role.USER);
            User testUser5 = new User("testUser5", "testUser5@gmail.com", "5.jpg", Role.USER);
            User testUser6 = new User("testUser6", "testUser6@gmail.com", "6.jpg", Role.USER);
            User testUser7 = new User("testUser7", "testUser7@gmail.com", "7.jpg", Role.USER);
            User testUser8 = new User("testUser8", "testUser8@gmail.com", "8.jpg", Role.USER);

            em.persist(testUser);
            em.persist(testUser2);
            em.persist(testUser3);
            em.persist(testUser4);
            em.persist(testUser5);
            em.persist(testUser6);
            em.persist(testUser7);
            em.persist(testUser8);

            for(int i=0; i<45; i++){
                User initUser = new User("initUser" + i, "initUser" + i + "@gmail.com", i + ".jpg", Role.USER);
                em.persist(initUser);
                initUser.setCreatedDate(LocalDateTime.now().plusMonths(-3));
            }


            testUser6.setCreatedDate(LocalDateTime.now().plusMonths(-1));
            testUser7.setCreatedDate(LocalDateTime.now().plusMonths(-1));
            testUser8.setCreatedDate(LocalDateTime.now().plusMonths(-2));


            //given
            Board board = new Board("board1");
            Board board2 = new Board("board2");
            Board board3 = new Board("board3");
            Board board4 = new Board("board4");
            Board board5 = new Board("board5");
            Board board6 = new Board("board6");

            board.changeUser(testUser);
            board2.changeUser(testUser);
            board3.changeUser(testUser);
            board4.changeUser(testUser2);
            board5.changeUser(testUser3);
            board6.changeUser(testUser3);

            em.persist(board);
            em.persist(board2);
            em.persist(board3);
            em.persist(board4);
            em.persist(board5);
            em.persist(board6);


            for (int a=10; a<30; a++){
                Board testBoard = new Board("testBoard" + a);
                testBoard.changeUser(testUser4);
                em.persist(testBoard);
            }

            Topic topic1 = new Topic("topic1", board);
            Topic topic2 = new Topic("topic2", board);
            Topic topic3 = new Topic("topic3", board4);
            em.persist(topic1);
            em.persist(topic2);
            em.persist(topic3);


            Card card1 = new Card("card1", "des1", topic1);
            Card card2 = new Card("card2", "des2", topic1);
            Card card3 = new Card("card3", "des3", topic2);
            Card card4 = new Card("card4", "des4", topic2);
            Card card5 = new Card("card5", "des5", topic2);
            Card card6 = new Card("card6", "des6", topic2);
            Card card7 = new Card("card7", "des7", topic3);

            em.persist(card1);
            em.persist(card2);
            em.persist(card3);
            em.persist(card4);
            em.persist(card5);
            em.persist(card6);
            em.persist(card7);

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
