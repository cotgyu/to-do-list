package com.toy.todolist.board.service;

import com.toy.todolist.board.domain.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@AutoConfigureMockMvc
@ActiveProfiles("test")
class BoardServiceTest {

    @Autowired
    EntityManager em;

    @Autowired
    BoardService boardService;

    @Autowired
    TopicRepository topicRepository;

    @Autowired
    BoardRepository boardRepository;

    @Autowired
    CardRepository cardRepository;

    @Test
    @Commit
    @DisplayName("토픽 전체 조회 쿼리 확인 테스트")
    public void findAllTopicTest(){
        //given
        Board board = new Board("board1");
        boardRepository.save(board);

        Topic topic1 = new Topic("topic1", board);
        Topic topic2 = new Topic("topic2", board);
        topicRepository.save(topic1);
        topicRepository.save(topic2);


        Card card1 = new Card("card1", "des1", topic1);
        Card card2 = new Card("card2", "des2", topic1);
        Card card3 = new Card("card3", "des3", topic2);
        Card card4 = new Card("card4", "des4", topic2);

        cardRepository.save(card1);
        cardRepository.save(card2);
        cardRepository.save(card3);
        cardRepository.save(card4);

        em.flush();
        em.clear();

        // when & then
        Board findBoard = boardRepository.findById(board.getId()).get();

        //Board findBoard2 = boardService.findAllContents(board.getId());

        List<Topic> topics = findBoard.getTopics();

        for (Topic topic : topics) {
            List<Card> cards = topic.getCards();

            for (Card card : cards) {
                System.out.println(card.getDescription());
            }
        }

    }

}