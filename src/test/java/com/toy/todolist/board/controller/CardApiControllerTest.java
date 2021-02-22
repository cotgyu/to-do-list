package com.toy.todolist.board.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.toy.todolist.board.domain.Card;
import com.toy.todolist.board.domain.CardRepository;
import com.toy.todolist.board.domain.Topic;
import com.toy.todolist.board.domain.TopicRepository;
import com.toy.todolist.board.dto.CardRequestDto;
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

import java.util.Optional;

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
class CardApiControllerTest {


    @Autowired
    EntityManager em;

    @Autowired
    CardRepository cardRepository;

    @Autowired
    TopicRepository topicRepository;

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected ObjectMapper objectMapper;

    @Test
    @DisplayName("카드 등록 api 테스트")
    public void addCardApiTest() throws Exception{
        //given
        CardRequestDto cardRequestDto = new CardRequestDto("cardName1");

        //when then
        mockMvc.perform(
                post("/api/card")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(objectMapper.writeValueAsString(cardRequestDto))
        )
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("resultMessage").value("success"));


    }

    @Test
    @DisplayName("카드 조회 api 테스트")
    public void findCardApiTest() throws Exception{
        //given
        Topic topic = new Topic("topic1");

        topicRepository.save(topic);

        Card card1 = new Card("card1", topic);

        cardRepository.save(card1);


        //when then
        mockMvc.perform(
                get("/api/card/{id}", card1.getId())
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
        )
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("resultMessage").value("success"))
                .andExpect(jsonPath("result").isNotEmpty());

    }

    @Test
    @DisplayName("카드 수정 api 테스트")
    public void updateCardApiTest() throws Exception{
        //given
        Topic topic = new Topic("topic1");
        topicRepository.save(topic);
        Card card1 = new Card("card1", "dis1" ,topic);
        cardRepository.save(card1);

        CardRequestDto cardRequestDto = new CardRequestDto("cardName1", "desName1");


        //when
        mockMvc.perform(
                put("/api/card/{id}", card1.getId())
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(objectMapper.writeValueAsString(cardRequestDto))
        )
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("resultMessage").value("success"))
                .andExpect(jsonPath("result").isNotEmpty());

        // then
        Card result = cardRepository.findById(1L).get();

        assertThat(result.getCardName()).isEqualTo(cardRequestDto.getCardName());
        assertThat(result.getDescription()).isEqualTo(cardRequestDto.getDescription());

    }
}