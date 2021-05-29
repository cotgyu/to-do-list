package com.toy.moduleweb.board.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.toy.board.domain.Board;
import com.toy.board.dto.BoardRequestDto;
import com.toy.board.repository.BoardRepository;
import com.toy.board.service.BoardService;
import com.toy.user.domain.Role;
import com.toy.user.domain.User;
import com.toy.user.domain.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Transactional
@AutoConfigureMockMvc
@ActiveProfiles("test")
class BoardApiControllerTest {

    @Autowired
    EntityManager em;

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected ObjectMapper objectMapper;

    @Autowired
    protected BoardRepository boardRepository;

    protected MockHttpSession mockHttpSession;
    protected MockHttpServletRequest request;

    @Autowired
    protected BoardService boardService;

    @Autowired
    protected UserRepository userRepository;

    @Test
    @DisplayName("보드 등록 api 테스트")
    public void addBoardApiTest() throws Exception{
        //given
        BoardRequestDto boardRequestDto = new BoardRequestDto("boardName1");

        User user = new User("testUser1", "email", "picture", Role.ADMIN);
        userRepository.save(user);

        // 이건 테스트 목안에서 유지되는 것 같고 실제 쏘는 곳은 다른 세션을 가져다쓰기때문에 문제가 되는 것같음
//        SessionUser sessionUser = new SessionUser(user);
//        mockHttpSession = new MockHttpSession();
//        mockHttpSession.setAttribute("user", sessionUser);
//        request = new MockHttpServletRequest();
//        request.setSession(mockHttpSession);
//        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        //when
        Long result = boardService.save(boardRequestDto, user.getEmail());

        Board findBoard = boardRepository.findById(result).get();

        // then
        Assertions.assertThat(result).isEqualTo(findBoard.getId());

        //when then
//        mockMvc.perform(
//                post("/api/board")
//                        .contentType(MediaType.APPLICATION_JSON_UTF8)
//                        .content(objectMapper.writeValueAsString(boardRequestDto))
//        )
//                .andDo(print())
//                .andExpect(status().is2xxSuccessful())
//                .andExpect(jsonPath("resultMessage").value("success"));

    }

    @Test
    @DisplayName("보드 수정 api 테스트")
    public void updateBoardApiTest() throws Exception{
        //given
        Board testBoard = new Board("testBoard");
        boardRepository.save(testBoard);

        BoardRequestDto boardRequestDto = new BoardRequestDto("boardName1");

        // TODO 수정 시에도 세션유저 체크함! 테스트 방법 고려해보기
//        //when
//        mockMvc.perform(
//                put("/api/board/" + testBoard.getId())
//                        .contentType(MediaType.APPLICATION_JSON_UTF8)
//                        .content(objectMapper.writeValueAsString(boardRequestDto))
//        )
//                .andDo(print())
//                .andExpect(status().is2xxSuccessful())
//                .andExpect(jsonPath("resultMessage").value("success"));
//
//        //then
//        Board updateBoard = boardRepository.findById(testBoard.getId()).get();
//
//        Assertions.assertThat(updateBoard.getBoardName()).isEqualTo(boardRequestDto.getBoardName());

    }


}