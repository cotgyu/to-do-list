package com.toy.todolist.board.controller;

import com.toy.todolist.board.dto.BoardRequestDto;
import com.toy.todolist.board.dto.TopicRequestDto;
import com.toy.todolist.board.dto.TopicResponseDto;
import com.toy.todolist.board.service.BoardService;
import com.toy.todolist.board.service.TopicService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/board")
public class BoardApiController {

    private final TopicService topicService;

    private final BoardService boardService;

    @PostMapping
    public ResponseEntity addBoard(@RequestBody BoardRequestDto boardRequestDto) {
        Map<String, Object> resultMap = new HashMap<>();

        Long result = boardService.save(boardRequestDto);

        resultMap.put("result", result);
        resultMap.put("resultMessage", "success");

        return new ResponseEntity<>(resultMap, HttpStatus.OK);

    }

    @PutMapping("/{boardId}")
    public ResponseEntity updateBoard(@PathVariable Long boardId, @RequestBody BoardRequestDto boardRequestDto){
        Map<String, Object> resultMap = new HashMap<>();

        Long updateBoardId = boardService.updateBoard(boardId, boardRequestDto);

        resultMap.put("result", updateBoardId);
        resultMap.put("resultMessage", "success");

        return new ResponseEntity<>(resultMap, HttpStatus.OK);
    }

}
