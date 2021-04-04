package com.toy.todolist.board.controller;

import com.toy.todolist.board.dto.BoardRequestDto;
import com.toy.todolist.board.dto.TopicRequestDto;
import com.toy.todolist.board.dto.TopicResponseDto;
import com.toy.todolist.board.service.BoardService;
import com.toy.todolist.board.service.TopicService;
import com.toy.todolist.config.dto.SessionUser;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/board")
public class BoardApiController {

    private final TopicService topicService;

    private final BoardService boardService;

    private final HttpSession httpSession;

    @PostMapping
    public ResponseEntity addBoard(@RequestBody BoardRequestDto boardRequestDto) {
        Map<String, Object> resultMap = new HashMap<>();

        SessionUser user = (SessionUser) httpSession.getAttribute("user");

        Long result = boardService.save(boardRequestDto, user.getEmail());

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
