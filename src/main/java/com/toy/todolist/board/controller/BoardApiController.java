package com.toy.todolist.board.controller;

import com.toy.todolist.board.dto.BoardRequestDto;
import com.toy.todolist.board.service.BoardService;
import com.toy.todolist.board.service.TopicService;
import com.toy.todolist.config.auth.LoginUser;
import com.toy.todolist.config.auth.dto.SessionUser;
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

    @PostMapping
    public ResponseEntity addBoard(@RequestBody BoardRequestDto boardRequestDto, @LoginUser SessionUser user) {
        Map<String, Object> resultMap = new HashMap<>();

        Long result = boardService.save(boardRequestDto, user.getEmail());

        resultMap.put("result", result);
        resultMap.put("resultMessage", "success");

        return new ResponseEntity<>(resultMap, HttpStatus.OK);

    }

    // TODO 시큐리티를 활용하여 rest api 인증 확인하기
    @PutMapping("/{boardId}")
    public ResponseEntity updateBoard(@PathVariable Long boardId, @RequestBody BoardRequestDto boardRequestDto, @LoginUser SessionUser user){
        Map<String, Object> resultMap = new HashMap<>();
        Long updateBoardId = boardService.updateBoard(boardId, boardRequestDto, user);

        if(updateBoardId == -1){
            resultMap.put("result", "해당 Board 수정 권한이 없습니다.");
            resultMap.put("resultMessage", "fail");

            return new ResponseEntity<>(resultMap, HttpStatus.UNAUTHORIZED);
        }

        resultMap.put("result", updateBoardId);
        resultMap.put("resultMessage", "success");

        return new ResponseEntity<>(resultMap, HttpStatus.OK);
    }

}
