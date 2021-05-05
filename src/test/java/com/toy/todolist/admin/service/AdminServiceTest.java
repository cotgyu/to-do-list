package com.toy.todolist.admin.service;

import com.querydsl.core.QueryResults;
import com.toy.todolist.user.domain.Role;
import com.toy.todolist.user.domain.User;
import com.toy.todolist.user.domain.UserRepository;
import com.toy.todolist.user.dto.MonthlyUserRegisterQueryDto;
import com.toy.todolist.user.dto.UserRequestDto;
import com.toy.todolist.user.dto.UserResponseDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.HashMap;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@AutoConfigureMockMvc
@ActiveProfiles("test")
class AdminServiceTest {

    @Autowired
    AdminService adminService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    EntityManager em;


    @Test
    @DisplayName("전체조회 정렬 테스트")
    public void findAllSort(){
        // given
        User testUser = new User("testUser1", "t1@gmail.com", "a", Role.ADMIN);
        User testUser2 = new User("testUser2", "t2@gmail.com", "a", Role.USER);
        User testUser3 = new User("testUser3", "t3@gmail.com", "a", Role.USER);
        User testUser4 = new User("testUser4", "t4@gmail.com", "a", Role.USER);

        userRepository.save(testUser);
        userRepository.save(testUser2);
        userRepository.save(testUser3);
        userRepository.save(testUser4);


        // when
        List<User> result = userRepository.findAll(Sort.by("createdDate"));
        List<UserResponseDto> result2 = adminService.getAllUserInfo();


        // then
        assertThat(result.size()).isEqualTo(userRepository.findAll().size());
        assertThat(result2.size()).isEqualTo(userRepository.findAll().size());

    }
    @DisplayName("사용자 수정 서비스테스트")
    @Test
    public void updateUserTest() throws Exception{
        //given
        User testUser = new User("testUser1", "t1@gmail.com", "a", Role.ADMIN);
        userRepository.save(testUser);

        UserRequestDto userRequestDto = new UserRequestDto(testUser.getName(), "updateEmail", testUser.getPicture(), testUser.getRole(), "Y");

        //when
        adminService.updateUser(testUser.getId(), userRequestDto);

        //then
        User user = userRepository.findById(testUser.getId()).get();

        assertThat(user.getEmail()).isEqualTo("updateEmail");
        assertThat(user.getDelFlag()).isEqualTo("Y");
    }

    @DisplayName("월별 가입자 통계 서비스 호출 테스트")
    @Test
    public void getMonthlyUserStatsServiceTest() throws Exception{
        //given
        int year = 2021;

        //when
        //List<MonthlyUserRegisterQueryDto> monthlyUserRegisterStatistics = userRepository.getMonthlyUserRegisterStatistics(year);
        HashMap<Integer, Long> monthlyUserRegisterStatistics2 = adminService.getMonthlyUserRegisterStatistics(year);

        //then
        assertThat(monthlyUserRegisterStatistics2.size()).isEqualTo(12);

    }
}