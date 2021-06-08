package com.toy.admin.controller;


import com.toy.admin.service.AdminService;
import com.toy.admin.dto.UserBoardStatsQueryDto;
import com.toy.user.dto.UserRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/admin")
public class AdminApiController {

    private final AdminService adminService;

    @PutMapping("/{userId}")
    public ResponseEntity updateUser(@PathVariable long userId, @RequestBody UserRequestDto userRequestDto){
        Map<String, Object> resultMap = new HashMap<>();

        adminService.updateUser(userId ,userRequestDto);

        resultMap.put("result", userId);
        resultMap.put("resultMessage", "success");

        return new ResponseEntity<>(resultMap, HttpStatus.OK);
    }

    @GetMapping("/monthlyUserStats/{year}")
    public ResponseEntity monthlyUserStats(@PathVariable int year) {
        Map<String, Object> resultMap = new HashMap<>();

        HashMap<Integer, Long> monthlyUserRegisterStatistics = adminService.getMonthlyUserRegisterStatistics(year);

        resultMap.put("result", monthlyUserRegisterStatistics);
        resultMap.put("resultMessage", "success");


        return new ResponseEntity<>(resultMap, HttpStatus.OK);
    }

    @GetMapping("/userBoardStatistics")
    public ResponseEntity userBoardStatistics() {
        Map<String, Object> resultMap = new HashMap<>();

        List<UserBoardStatsQueryDto> allUserBoardStatistics = adminService.getAllUserBoardStatistics();

        resultMap.put("result", allUserBoardStatistics);
        resultMap.put("resultMessage", "success");


        return new ResponseEntity<>(resultMap, HttpStatus.OK);
    }



}
