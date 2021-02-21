package com.toy.todolist.board.controller;

import com.toy.todolist.board.dto.TopicRequestDto;
import com.toy.todolist.board.service.TopicService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/topic")
public class TopicApiController {

    private final TopicService topicService;

    @PostMapping
    public ResponseEntity addTopic(@RequestBody TopicRequestDto topicRequestDto) {
        Map<String, Object> resultMap = new HashMap<>();

        Long result = topicService.save(topicRequestDto);

        resultMap.put("result", result);
        resultMap.put("resultMessage", "success");

        return new ResponseEntity<>(resultMap, HttpStatus.OK);

    }
}
