package com.toy.admin.service;


import com.toy.admin.domain.MonthlyUserRegisterStats;
import com.toy.admin.domain.UserBoardStats;
import com.toy.admin.repository.MonthlyUserRegisterStatsRepository;
import com.toy.admin.repository.UserBoardStatsRepository;
import com.toy.user.domain.User;
import com.toy.user.repository.UserRepository;
import com.toy.admin.dto.UserBoardStatsQueryDto;
import com.toy.user.dto.UserRequestDto;
import com.toy.user.dto.UserResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class AdminService {

    private final UserRepository userRepository;
    
    private final UserBoardStatsRepository userBoardStatsRepository;
    
    private final MonthlyUserRegisterStatsRepository monthlyUserRegisterStatsRepository;
    
    @Transactional(readOnly = true)
    public List<UserResponseDto> getAllUserInfo(){

        List<User> userList = userRepository.findAll(Sort.by("createdDate"));

        List<UserResponseDto> result = userList.stream()
                .map(user -> new UserResponseDto(user))
                .collect(Collectors.toList());

        return result;
    }

    @Transactional
    public void updateUser (long userId, UserRequestDto userRequestDto){
        User findUser = findUserById(userId);
        findUser.update(userRequestDto);
    }

    private User findUserById(long userId) {
        return userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다. id = " + userId));
    }

    @Transactional(readOnly = true)
    public HashMap<Integer, Long> getMonthlyUserRegisterStatistics(int year){

        //List<MonthlyUserRegisterQueryDto> monthlyUserRegisterStatistics = userRepository.getMonthlyUserRegisterStatistics(year);

        // TODO - 연별로 데이터 뽑을 수 있게 수정 필요
        List<MonthlyUserRegisterStats> allData = monthlyUserRegisterStatsRepository.findAll();

        HashMap<Integer, Long> resultMap = new HashMap<>();

        for (int i = 1; i<=12; i++){
            resultMap.put(i, 0L);
        }

        allData
                .stream()
                .map(k -> resultMap.put( k.getMonth() - year*100, k.getCount()))
                .collect(Collectors.toList());

        return resultMap;
    }

    @Transactional(readOnly = true)
    public List<UserBoardStatsQueryDto> getAllUserBoardStatistics(){

        List<UserBoardStats> allData = userBoardStatsRepository.findAll();

        List<UserBoardStatsQueryDto> resultData = allData
                .stream()
                .map(k -> new UserBoardStatsQueryDto(k))
                .collect(Collectors.toList());

        //return userRepository.getUserBoardStatistics();
        return resultData;
    }

}
