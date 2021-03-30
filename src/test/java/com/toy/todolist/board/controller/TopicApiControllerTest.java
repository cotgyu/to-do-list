package com.toy.todolist.board.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.toy.todolist.board.domain.*;
import com.toy.todolist.board.dto.CardRequestDto;
import com.toy.todolist.board.dto.TopicRequestDto;
import com.toy.todolist.board.service.TopicService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Transactional
@AutoConfigureMockMvc
@ActiveProfiles("test")
class TopicApiControllerTest {
    @Autowired
    EntityManager em;

    @Autowired
    TopicRepository topicRepository;

    @Autowired
    CardRepository cardRepository;

    @Autowired
    BoardRepository boardRepository;

    @Autowired
    TopicService topicService;

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected ObjectMapper objectMapper;

    @Test
    @DisplayName("토픽 등록 api 테스트")
    public void addTopicApiTest() throws Exception{
        //given
        Board boardTest1 = new Board("boardTest1");
        boardRepository.save(boardTest1);

        TopicRequestDto topicRequestDto = new TopicRequestDto(boardTest1.getId(), "topicName1");

        //when then
        mockMvc.perform(
                post("/api/topic")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(objectMapper.writeValueAsString(topicRequestDto))
        )
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("resultMessage").value("success"));

    }

    @Test
    @DisplayName("토픽 조회 api 테스트")
    public void findTopicApiTest() throws Exception{
        //given
        Topic topic = new Topic("topic1");

        topicRepository.save(topic);

        Card card1 = new Card("card1", topic);

        cardRepository.save(card1);


        //when then
        mockMvc.perform(
                get("/api/topic/{id}", topic.getId())
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
        )
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("resultMessage").value("success"))
                .andExpect(jsonPath("result").isNotEmpty());

    }

    @Test
    @DisplayName("토픽 수정 api 테스트")
    public void updateTopicApiTest() throws Exception{
        //given
        Topic topic = new Topic("topic1");
        topicRepository.save(topic);
        Card card1 = new Card("card1", topic);
        cardRepository.save(card1);

        TopicRequestDto topicRequestDto = new TopicRequestDto("topicName1");


        //when
        mockMvc.perform(
                put("/api/topic/{id}", topic.getId())
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(objectMapper.writeValueAsString(topicRequestDto))
        )
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("resultMessage").value("success"))
                .andExpect(jsonPath("result").isNotEmpty());

        // then
        Topic result = topicRepository.findById(topic.getId()).get();


        assertThat(result.getTopicName()).isEqualTo(topicRequestDto.getTopicName());
    }
}