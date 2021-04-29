package com.toy.todolist.admin.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.toy.todolist.board.domain.Board;
import com.toy.todolist.board.dto.BoardRequestDto;
import com.toy.todolist.board.repository.BoardRepository;
import com.toy.todolist.board.service.BoardService;
import com.toy.todolist.user.domain.Role;
import com.toy.todolist.user.domain.User;
import com.toy.todolist.user.domain.UserRepository;
import com.toy.todolist.user.dto.UserRequestDto;
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

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@Transactional
@AutoConfigureMockMvc
@ActiveProfiles("test")
class AdminApiControllerTest {


    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected ObjectMapper objectMapper;

    @Autowired
    protected BoardRepository boardRepository;

    @Autowired
    protected UserRepository userRepository;


    @Test
    @DisplayName("사용자 수정 api 테스트")
    public void updateUserApiTest() throws Exception{
        //given
        User testUser = new User("testUser1", "t1@gmail.com", "a", Role.ADMIN);
        userRepository.save(testUser);

        UserRequestDto userRequestDto = new UserRequestDto(testUser.getName(), "updateEmail", testUser.getPicture(), testUser.getRole(), "Y");

        //when
        mockMvc.perform(
                put("/api/admin/" + testUser.getId())
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(objectMapper.writeValueAsString(userRequestDto))
        )
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("resultMessage").value("success"));

        //then
        User user = userRepository.findById(testUser.getId()).get();

        assertThat(user.getEmail()).isEqualTo("updateEmail");
        assertThat(user.getDelFlag()).isEqualTo("Y");
    }

}