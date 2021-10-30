package com.toy.board.service;

import com.toy.board.domain.Board;
import com.toy.board.domain.Card;
import com.toy.board.domain.Topic;
import com.toy.board.dto.BoardRequestDto;
import com.toy.board.dto.BoardResponseDto;
import com.toy.board.dto.TopicResponseDto;
import com.toy.board.repository.BoardRepository;
import com.toy.board.repository.CardRepository;
import com.toy.board.repository.TopicRepository;
import com.toy.config.auth.dto.SessionUser;
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
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
    @DisplayName("board 저장 - 테스트")
    public void save_Test() throws Exception{
        //given
        User testUser = new User("testUser", "testSaveUser@gmail.com", "1.jpg", Role.ADMIN);
        userRepository.save(testUser);
        em.flush();
        em.clear();

        BoardRequestDto boardRequestDto = new BoardRequestDto("boardSaveName1");

        //when
        Long saveId = boardService.save(boardRequestDto, testUser.getEmail());
        Board board = boardRepository.findById(saveId).get();

        //then
        assertThat(board.getBoardName()).isEqualTo(boardRequestDto.getBoardName());
    }

    @Test
    @DisplayName("board 저장 - 잘못된 사용자 email로 저장 실패 테스트")
    public void wrongUser_save_failTest() throws Exception{
        //given
        User testUser = new User("testUser", "v123v123s@gmail.com", "1.jpg", Role.ADMIN);
        userRepository.save(testUser);
        em.flush();
        em.clear();

        BoardRequestDto boardRequestDto = new BoardRequestDto("boardName1");

        //when then
        String wrongEmail = "wrong!!";
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            boardService.save(boardRequestDto, wrongEmail);
        });

        assertThat(exception.getMessage()).isEqualTo("해당 User가 없습니다. email="+wrongEmail);
    }

    @Test
    @Commit
    @DisplayName("토픽 전체 조회 - 쿼리 확인 테스트")
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
    @DisplayName("컨텐츠 모두 조회 - 테스트")
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

    @Test
    @DisplayName("컨텐츠 모두 조회 - 잘못된 boardId로 조회 실패 테스트")
    public void findAllContents_wrongId_failTest() throws Exception{
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

        //when then
        long wrongId = 99999L;
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            boardService.findAllContents(wrongId);
        });

        assertThat(exception.getMessage()).isEqualTo("해당 Board가 없습니다. id="+wrongId);
    }

    @Test
    @Commit
    @DisplayName("board 수정 - 잘못된 사용자로 수정 시 실패 테스트")
    public void updateBoard_wrongUser_failTest() throws Exception{
        //given
        User testUser = new User("testUser", "v123v123s@gmail.com", "1.jpg", Role.ADMIN);
        userRepository.save(testUser);

        User testUser2 = new User("testUser2", "v123v123s2@gmail.com", "2.jpg", Role.USER);
        userRepository.save(testUser2);

        Board board = new Board("board1", testUser);
        boardRepository.save(board);

        Topic topic1 = new Topic("topic1", board);
        Topic topic2 = new Topic("topic2", board);
        topicRepository.save(topic1);
        topicRepository.save(topic2);

        em.flush();
        em.clear();

        //when
        BoardRequestDto boardRequestDto = new BoardRequestDto("boardName1");
        SessionUser sessionUser = new SessionUser(testUser2);

        Long result = boardService.updateBoard(board.getId(), boardRequestDto, sessionUser);

        //then
        assertThat(result).isEqualTo(-1L);
    }
    
    @Test
    @Commit
    @DisplayName("boardList 조회 - 테스트")
    public void findAllBoardListByEmailTest() throws Exception{
        //given
        User testUser = new User("testUser", "testUser@gmail.com", "https://lh6.googleusercontent.com/-XRdI0_dL6cQ/AAAAAAAAAAI/AAAAAAAAAAA/AMZuucm6Uc0lgVjtVlZQFcP1U69RETkOfA/s96-c/photo.jpg", Role.ADMIN);
        userRepository.save(testUser);

        Board board = new Board("board1", testUser);
        Board board2 = new Board("board2", testUser);
        boardRepository.save(board);
        boardRepository.save(board2);


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
        List<BoardResponseDto> allBoardList = boardService.findAllBoardListByEmail(testUser.getEmail());

        //then
        assertThat(allBoardList.size()).isEqualTo(2);
    }

    @Test
    @DisplayName("boardList 조회 - 잘못된 사용자 email로 조회 시 exception 발생 테스트")
    public void findAllBoardListByEmail_wrongEmail_failTest() throws Exception{
        //given
        User testUser = new User("testUser", "testUser@gmail.com", "https://lh6.googleusercontent.com/-XRdI0_dL6cQ/AAAAAAAAAAI/AAAAAAAAAAA/AMZuucm6Uc0lgVjtVlZQFcP1U69RETkOfA/s96-c/photo.jpg", Role.ADMIN);
        userRepository.save(testUser);

        Board board = new Board("board1", testUser);
        Board board2 = new Board("board2", testUser);
        boardRepository.save(board);
        boardRepository.save(board2);

        em.flush();
        em.clear();

        //when then
        String wrongEmail = "wrong!!";
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            boardService.findAllBoardListByEmail(wrongEmail);
        });

        assertThat(exception.getMessage()).isEqualTo("해당 User가 없습니다. email="+wrongEmail);
    }
}