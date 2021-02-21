package com.toy.todolist.board.controller;

import com.toy.todolist.board.dto.CardRequestDto;
import com.toy.todolist.board.dto.CardResponseDto;
import com.toy.todolist.board.dto.TopicRequestDto;
import com.toy.todolist.board.dto.TopicResponseDto;
import com.toy.todolist.board.service.TopicService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/{id}")
    public ResponseEntity findTopic(@PathVariable Long id){
        Map<String, Object> resultMap = new HashMap<>();

        TopicResponseDto topic = topicService.findTopic(id);

        resultMap.put("result", topic);
        resultMap.put("resultMessage", "success");

        return new ResponseEntity<>(resultMap, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity updateTopic(@PathVariable Long id, @RequestBody TopicRequestDto topicRequestDto){
        Map<String, Object> resultMap = new HashMap<>();

        topicService.update(id, topicRequestDto);

        resultMap.put("result", id);
        resultMap.put("resultMessage", "success");

        return new ResponseEntity<>(resultMap, HttpStatus.OK);
    }
}
