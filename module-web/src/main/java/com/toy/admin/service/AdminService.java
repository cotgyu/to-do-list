package com.toy.admin.service;


import com.toy.user.domain.User;
import com.toy.user.domain.UserRepository;
import com.toy.user.dto.MonthlyUserRegisterQueryDto;
import com.toy.user.dto.UserBoardStatsQueryDto;
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

        List<MonthlyUserRegisterQueryDto> monthlyUserRegisterStatistics = userRepository.getMonthlyUserRegisterStatistics(year);

        HashMap<Integer, Long> resultMap = new HashMap<>();

        for (int i = 1; i<=12; i++){
            resultMap.put(i, 0L);
        }

        monthlyUserRegisterStatistics
                .stream()
                .map(k -> resultMap.put( k.getMonth() - year*100, k.getCount()))
                .collect(Collectors.toList());


        return resultMap;
    }

    @Transactional(readOnly = true)
    public List<UserBoardStatsQueryDto> getAllUserBoardStatistics(){

        return userRepository.getUserBoardStatistics();
    }

}
