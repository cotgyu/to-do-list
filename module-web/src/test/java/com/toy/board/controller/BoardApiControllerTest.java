package com.toy.board.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.toy.board.domain.Board;
import com.toy.board.dto.BoardRequestDto;
import com.toy.board.repository.BoardRepository;
import com.toy.board.service.BoardService;
import com.toy.config.auth.dto.SessionUser;
import com.toy.user.domain.Role;
import com.toy.user.domain.User;
import com.toy.user.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.springframework.restdocs.hypermedia.HypermediaDocumentation.linkWithRel;
import static org.springframework.restdocs.hypermedia.HypermediaDocumentation.links;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.relaxedResponseFields;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Transactional
@AutoConfigureMockMvc
@AutoConfigureRestDocs
@ActiveProfiles("test")
class BoardApiControllerTest {

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
    @Autowired
    EntityManager em;

    @Test
    @DisplayName("보드 등록 api 테스트")
    void addBoardApiTest() throws Exception {
        //given
        BoardRequestDto boardRequestDto = new BoardRequestDto("boardName1");

        User user = new User("testUser1", "email", "picture", Role.ADMIN);
        userRepository.save(user);

        // 세션 정보
        SessionUser sessionUser = new SessionUser(user);
        mockHttpSession = new MockHttpSession();
        mockHttpSession.setAttribute("user", sessionUser);

        //when then
        mockMvc.perform(
                        post("/api/board")
                                .session(mockHttpSession)
                                .contentType(MediaType.APPLICATION_JSON_UTF8)
                                .accept(MediaTypes.HAL_JSON)
                                .content(objectMapper.writeValueAsString(boardRequestDto))
                )
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("_links.update-board").exists())
                .andDo(document("create-board",
                        links(
                                linkWithRel("profile").description("link to profile"),
                                linkWithRel("self").description("link to self"),
                                linkWithRel("update-board").description("link to update an existing board")
                        ),
                        relaxedResponseFields(
                                fieldWithPath("id").description("identifier of new Board"),
                                fieldWithPath("boardName").description("name of new Board"),
                                fieldWithPath("delFlag").description("delFlag of new Board")
                        )
                ))
        ;

    }

    @Test
    @DisplayName("보드 등록 api Bad Request 테스트")
    void addBoardApiBadRequestTest() throws Exception {
        //given
        BoardRequestDto boardRequestDto = new BoardRequestDto("");

        User user = new User("testUser1", "email", "picture", Role.ADMIN);
        userRepository.save(user);

        // 세션 정보
        SessionUser sessionUser = new SessionUser(user);
        mockHttpSession = new MockHttpSession();
        mockHttpSession.setAttribute("user", sessionUser);

        //when then
        mockMvc.perform(
                        post("/api/board")
                                .session(mockHttpSession)
                                .contentType(MediaType.APPLICATION_JSON_UTF8)
                                .accept(MediaTypes.HAL_JSON)
                                .content(objectMapper.writeValueAsString(boardRequestDto))
                )
                .andDo(print())
                .andExpect(status().isBadRequest())
        ;

    }


    @Test
    @DisplayName("보드 수정 api 테스트")
    void updateBoardApiTest() throws Exception {
        //given
        Board testBoard = new Board("testBoard");
        ;
        boardRepository.save(testBoard);

        BoardRequestDto boardRequestDto = new BoardRequestDto("boardName1");

        // 세션 정보
        User user = new User("testUser1", "email", "picture", Role.ADMIN);
        userRepository.save(user);

        testBoard.changeUser(user);

        SessionUser sessionUser = new SessionUser(user);
        mockHttpSession = new MockHttpSession();
        mockHttpSession.setAttribute("user", sessionUser);

        //when
        mockMvc.perform(
                        put("/api/board/" + testBoard.getId())
                                .contentType(MediaType.APPLICATION_JSON_UTF8)
                                .session(mockHttpSession)
                                .content(objectMapper.writeValueAsString(boardRequestDto))
                )
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("_links.get-board").exists())
                .andDo(document("update-board",
                        links(
                                linkWithRel("profile").description("link to profile"),
                                linkWithRel("self").description("link to self"),
                                linkWithRel("get-board").description("link to get an existing board")
                        ),
                        relaxedResponseFields(
                                fieldWithPath("id").description("identifier of new Board"),
                                fieldWithPath("boardName").description("name of new Board"),
                                fieldWithPath("delFlag").description("delFlag of new Board")
                        ))
                );

        //then
        Board updateBoard = boardRepository.findById(testBoard.getId()).get();

        Assertions.assertThat(updateBoard.getBoardName()).isEqualTo(boardRequestDto.getBoardName());

    }


}