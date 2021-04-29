package com.toy.todolist.admin.controller;

import com.toy.todolist.admin.service.AdminService;
import com.toy.todolist.user.dto.UserRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
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



}
