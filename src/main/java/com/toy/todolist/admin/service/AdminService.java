package com.toy.todolist.admin.service;

import com.toy.todolist.user.domain.User;
import com.toy.todolist.user.domain.UserRepository;
import com.toy.todolist.user.dto.UserRequestDto;
import com.toy.todolist.user.dto.UserResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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
}
