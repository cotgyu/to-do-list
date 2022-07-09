package com.toy.admin.service;


import com.toy.admin.domain.MonthlyUserRegisterStats;
import com.toy.admin.domain.UserBoardStats;
import com.toy.admin.dto.UserBoardStatsQueryDto;
import com.toy.admin.repository.MonthlyUserRegisterStatsRepository;
import com.toy.admin.repository.UserBoardStatsRepository;
import com.toy.redis.domain.MonthlyUserRegisterStatsRedis;
import com.toy.redis.domain.UserBoardStatsRedis;
import com.toy.redis.repository.MonthlyUserRegisterStatsRedisRepository;
import com.toy.redis.repository.UserBoardStatsRedisRepository;
import com.toy.user.domain.User;
import com.toy.user.dto.UserRequestDto;
import com.toy.user.dto.UserResponseDto;
import com.toy.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class AdminService {

    private final UserRepository userRepository;

    private final UserBoardStatsRepository userBoardStatsRepository;

    private final MonthlyUserRegisterStatsRepository monthlyUserRegisterStatsRepository;

    private final UserBoardStatsRedisRepository userBoardStatsRedisRepository;

    private final MonthlyUserRegisterStatsRedisRepository monthlyUserRegisterStatsRedisRepository;

    @Transactional(readOnly = true)
    public List<UserResponseDto> getAllUserInfo() {
        List<User> userList = userRepository.findAll(Sort.by("createdDate"));

        return userList.stream()
                .map(user -> new UserResponseDto(user))
                .collect(Collectors.toList());
    }

    @Transactional
    public void updateUser(long userId, UserRequestDto userRequestDto) {
        User findUser = findUserById(userId);
        findUser.update(userRequestDto);
    }

    private User findUserById(long userId) {
        return userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다. id = " + userId));
    }

    @Transactional(readOnly = true)
    public Map<Integer, Long> getMonthlyUserRegisterStatistics(int year) {

        Optional<MonthlyUserRegisterStatsRedis> monthlyUserRegisterStatsRedis = monthlyUserRegisterStatsRedisRepository.findById("monthlyUserRegisterStatsRedis");
        if (monthlyUserRegisterStatsRedis.isPresent()) {
            return mapToMonthlyData(monthlyUserRegisterStatsRedis.get().getUserRegisterStats());
        }

        // 직접 통계 조회
        //List<MonthlyUserRegisterQueryDto> monthlyUserRegisterStatistics = userRepository.getMonthlyUserRegisterStatistics(year);
        // TODO - 연별로 데이터 뽑을 수 있게 수정 필요
        List<MonthlyUserRegisterStats> userRegisterStats = monthlyUserRegisterStatsRepository.findAll();
        // redis 저장
        monthlyUserRegisterStatsRedisRepository.save(MonthlyUserRegisterStatsRedis.builder()
                .id("monthlyUserRegisterStatsRedis")
                .userRegisterStats(userRegisterStats)
                .build()
        );

        return mapToMonthlyData(userRegisterStats);
    }

    private HashMap<Integer, Long> mapToMonthlyData(List<MonthlyUserRegisterStats> monthlyUserRegisterStats) {
        HashMap<Integer, Long> resultMap = new HashMap<>();
        for (int i = 1; i <= 12; i++) {
            resultMap.put(i, 0L);
        }

        if (monthlyUserRegisterStats.isEmpty()) {
            return resultMap;
        }

        monthlyUserRegisterStats
                .stream()
                .map(k -> resultMap.put(k.getMonth(), k.getCount()))
                .collect(Collectors.toList());

        return resultMap;
    }

    @Transactional(readOnly = true)
    public List<UserBoardStatsQueryDto> getAllUserBoardStatistics() {

        Optional<UserBoardStatsRedis> userBoardStatsRedis = userBoardStatsRedisRepository.findById("userBoardStatsRedis");
        if (userBoardStatsRedis.isPresent()) {
            return userBoardStatsRedis.get().getUserBoardStats().stream()
                    .map(k -> new UserBoardStatsQueryDto(k))
                    .collect(Collectors.toList());
        }

        List<UserBoardStats> allData = userBoardStatsRepository.findAll();
        // redis 저장
        userBoardStatsRedisRepository.save(UserBoardStatsRedis.builder()
                .id("userBoardStatsRedis")
                .userBoardStats(allData)
                .build()
        );

        // 직접 통계 조회
        //return userRepository.getUserBoardStatistics();
        return allData
                .stream()
                .map(k -> new UserBoardStatsQueryDto(k))
                .collect(Collectors.toList());
    }

}
