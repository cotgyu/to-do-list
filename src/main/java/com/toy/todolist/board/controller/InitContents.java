package com.toy.todolist.board.controller;

import com.toy.todolist.board.domain.Board;
import com.toy.todolist.board.domain.Card;
import com.toy.todolist.board.domain.Topic;
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


        }

    }
}
