package com.toy.todolist.board.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.toy.todolist.board.domain.Board;
import com.toy.todolist.board.domain.BoardRepository;
import com.toy.todolist.board.dto.BoardRequestDto;
import com.toy.todolist.board.dto.TopicRequestDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
    BoardRepository boardRepository;

    @Test
    @DisplayName("보드 등록 api 테스트")
    public void addBoardApiTest() throws Exception{
        //given
        BoardRequestDto boardRequestDto = new BoardRequestDto("boardName1");

        //when then
        mockMvc.perform(
                post("/api/board")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(objectMapper.writeValueAsString(boardRequestDto))
        )
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("resultMessage").value("success"));

    }

    @Test
    @DisplayName("보드 수정 api 테스트")
    public void updateBoardApiTest() throws Exception{
        //given
        Board testBoard = new Board("testBoard");
        boardRepository.save(testBoard);

        BoardRequestDto boardRequestDto = new BoardRequestDto("boardName1");

        //when
        mockMvc.perform(
                put("/api/board/" + testBoard.getId())
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(objectMapper.writeValueAsString(boardRequestDto))
        )
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("resultMessage").value("success"));

        //then
        Board updateBoard = boardRepository.findById(testBoard.getId()).get();

        Assertions.assertThat(updateBoard.getBoardName()).isEqualTo(boardRequestDto.getBoardName());

    }


}