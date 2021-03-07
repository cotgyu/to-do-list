package com.toy.todolist.board.controller;

import com.toy.todolist.board.domain.*;
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

            //given
            Board board = new Board("board1");
            em.persist(board);

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
            CheckList checkList2 = new CheckList("checkList2", card3);
            em.persist(checkList1);
            em.persist(checkList2);

            CheckItem checkItem1 = new CheckItem("checkItem1",checkList1);
            CheckItem checkItem2 = new CheckItem("checkItem2",checkList1);
            CheckItem checkItem3 = new CheckItem("checkItem3",checkList1);
            CheckItem checkItem4 = new CheckItem("checkItem4",checkList2);
            CheckItem checkItem5 = new CheckItem("checkItem5",checkList2);

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

            Label label1 = new Label("label1", "black");
            Label label2 = new Label("label2", "red");
            Label label3 = new Label("label3", "blue");

            em.persist(label1);
            em.persist(label2);
            em.persist(label3);

            CardLabel cardLabel1 = new CardLabel(card1, label1);
            CardLabel cardLabel2 = new CardLabel(card2, label2);
            CardLabel cardLabel3 = new CardLabel(card1, label3);
            CardLabel cardLabel4 = new CardLabel(card4, label3);

            card1.addCardLabel(cardLabel1);
            card2.addCardLabel(cardLabel2);
            card1.addCardLabel(cardLabel3);
            card4.addCardLabel(cardLabel4);



        }

    }
}
