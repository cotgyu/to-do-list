package com.toy.board.service;

import com.toy.board.domain.Board;
import com.toy.board.domain.Card;
import com.toy.board.domain.Topic;
import com.toy.board.dto.BoardResponseDto;
import com.toy.board.dto.TopicResponseDto;
import com.toy.board.repository.BoardRepository;
import com.toy.board.repository.CardRepository;
import com.toy.board.repository.TopicRepository;
import com.toy.user.domain.Role;
import com.toy.user.domain.User;
import com.toy.user.repository.UserRepository;
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

import static org.assertj.core.api.Assertions.assertThat;

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

    @Autowired
    UserRepository userRepository;

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
        List<Topic> topics = topicRepository.findAllByBoard_idAndDelFlag(board.getId(), "N");

        for (Topic topic : topics) {
            List<Card> cards = topic.getCards();

            for (Card card : cards) {
                System.out.println(card.getDescription());
            }
        }
    }

    @Test
    @Commit
    @DisplayName("컨텐츠 모두 조회 테스트")
    public void findAllContentsTest() throws Exception{
        //given
        User testUser = new User("testUser", "v123v123s@gmail.com", "https://lh6.googleusercontent.com/-XRdI0_dL6cQ/AAAAAAAAAAI/AAAAAAAAAAA/AMZuucm6Uc0lgVjtVlZQFcP1U69RETkOfA/s96-c/photo.jpg", Role.ADMIN);
        userRepository.save(testUser);

        Board board = new Board("board1", testUser);
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

        //when
        BoardResponseDto allContents = boardService.findAllContents(board.getId());
        List<TopicResponseDto> topics = allContents.getTopics();

        //then
        assertThat(topics.size()).isEqualTo(2);
    }

}