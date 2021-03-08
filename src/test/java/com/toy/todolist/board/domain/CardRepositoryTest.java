package com.toy.todolist.board.domain;

import jdk.jfr.Description;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
class CardRepositoryTest {

    @Autowired
    EntityManager em;

    @Autowired
    CardRepository cardRepository;

    @Autowired
    TopicRepository topicRepository;

    @Test
    @DisplayName("card, topic 등록 테스트")
    public void card_save_test(){

        Topic topic = new Topic("topic1");

        topicRepository.save(topic);

        Card card1 = new Card("card1", topic);
        Card card2 = new Card("card2", topic);
        Card card3 = new Card("card3", topic);

        cardRepository.save(card1);
        cardRepository.save(card2);
        cardRepository.save(card3);

        em.flush();
        em.clear();

        List<Card> cards = cardRepository.findAll();

        for (Card card : cards) {
            System.out.println("topicName : "+ card.getTopic().getTopicName());
        }

        assertThat(cards.get(0).getTopic().getTopicName()).isEqualTo(topic.getTopicName());
    }

}