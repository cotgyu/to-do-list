package com.toy.board.controller;


import com.toy.board.dto.TopicRequestDto;
import com.toy.board.dto.TopicResponseDto;
import com.toy.board.service.TopicService;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/topic")
public class TopicApiController {

    private final TopicService topicService;

    @PostMapping
    public ResponseEntity addTopic(@RequestBody TopicRequestDto topicRequestDto) {
        Long result = topicService.save(topicRequestDto);

        WebMvcLinkBuilder webMvcLinkBuilder = linkTo(CardApiController.class).slash(result);

        EntityModel<TopicRequestDto> entityModel = EntityModel.of(topicRequestDto);
        entityModel.add(webMvcLinkBuilder.withSelfRel());
        entityModel.add(webMvcLinkBuilder.withRel("select-topic"));

        return ResponseEntity.created(webMvcLinkBuilder.toUri()).body(entityModel);
    }

    @GetMapping("/{id}")
    public ResponseEntity findTopic(@PathVariable Long id) {
        Map<String, Object> resultMap = new HashMap<>();

        TopicResponseDto topic = topicService.findTopic(id);

        resultMap.put("result", topic);
        resultMap.put("resultMessage", "success");

        return new ResponseEntity<>(resultMap, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity updateTopic(@PathVariable Long id, @RequestBody TopicRequestDto topicRequestDto) {
        topicService.update(id, topicRequestDto);

        WebMvcLinkBuilder webMvcLinkBuilder = linkTo(CardApiController.class).slash(id);

        EntityModel<TopicRequestDto> entityModel = EntityModel.of(topicRequestDto);
        entityModel.add(webMvcLinkBuilder.withSelfRel());
        entityModel.add(webMvcLinkBuilder.withRel("select-topic"));

        return ResponseEntity.created(webMvcLinkBuilder.toUri()).body(entityModel);
    }
}
