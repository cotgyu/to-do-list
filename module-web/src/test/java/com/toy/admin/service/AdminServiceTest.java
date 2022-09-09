package com.toy.admin.service;

import com.toy.admin.dto.UserBoardStatsQueryDto;
import com.toy.redis.repository.MonthlyUserRegisterStatsRedisRepository;
import com.toy.redis.repository.UserBoardStatsRedisRepository;
import com.toy.user.domain.Role;
import com.toy.user.domain.User;
import com.toy.user.dto.UserRequestDto;
import com.toy.user.dto.UserResponseDto;
import com.toy.user.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

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

    @Autowired
    UserBoardStatsRedisRepository userBoardStatsRedisRepository;

    @Autowired
    MonthlyUserRegisterStatsRedisRepository monthlyUserRegisterStatsRedisRepository;


    @Test
    @DisplayName("전체조회 정렬 테스트")
    void findAllSort() {
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
    void updateUserTest() throws Exception {
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
    void getMonthlyUserStatsServiceTest() throws Exception {
        //given
        int year = 2021;

        //when
        //List<MonthlyUserRegisterQueryDto> monthlyUserRegisterStatistics = userRepository.getMonthlyUserRegisterStatistics(year);
        Map<Integer, Long> monthlyUserRegisterStatistics2 = adminService.getMonthlyUserRegisterStatistics(year);

        //then
        assertThat(monthlyUserRegisterStatistics2.size()).isEqualTo(12);

    }

    @DisplayName("사용자 board 사용 통계 테스트")
    @Test
    void getUserBoardStatsService() throws Exception {
        //given

        //when
        //List<UserBoardStatsQueryDto> userBoardStatics = userRepository.getUserBoardStatistics();
        List<UserBoardStatsQueryDto> allUserBoardStatistics = adminService.getAllUserBoardStatistics();


        //then TODO 통계 테이블 생성으로 테스트 중지 다른방법 생각할 것
        //assertThat(allUserBoardStatistics.size()).isEqualTo(2);
        //assertThat(allUserBoardStatistics.isEmpty()).isEqualTo(false);
        System.out.println("size: " + allUserBoardStatistics.size());
    }

    @DisplayName("통계 조회 부분 Redis 적용 테스트")
    @Test
    void getRedis() throws Exception {
        // init
        userBoardStatsRedisRepository.deleteAll();
        monthlyUserRegisterStatsRedisRepository.deleteAll();

        // given & when
        adminService.getAllUserBoardStatistics();
        adminService.getMonthlyUserRegisterStatistics(1);

        // then
        assertThat(userBoardStatsRedisRepository.findById("userBoardStatsRedis").isPresent()).isTrue();
        assertThat(monthlyUserRegisterStatsRedisRepository.findById("monthlyUserRegisterStatsRedis").isPresent()).isTrue();
    }
}