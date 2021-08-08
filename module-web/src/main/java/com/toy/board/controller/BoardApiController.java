package com.toy.board.controller;


import com.toy.board.dto.BoardRequestDto;
import com.toy.board.service.BoardService;
import com.toy.board.service.TopicService;
import com.toy.common.CommonResource;
import com.toy.config.auth.LoginUser;
import com.toy.config.auth.dto.SessionUser;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/board")
public class BoardApiController {

    private final TopicService topicService;

    private final BoardService boardService;

    // TODO
    // validator 생성 처리
    // docs 한번 생각
    @PostMapping
    public ResponseEntity addBoard(@RequestBody @Valid BoardRequestDto boardRequestDto, @LoginUser SessionUser user) {


        Long result = boardService.save(boardRequestDto, user.getEmail());

        WebMvcLinkBuilder webMvcLinkBuilder = linkTo(BoardApiController.class).slash(result);
        URI uri = webMvcLinkBuilder.toUri();

        // update 링크 제공
        CommonResource commonResource = new CommonResource(boardRequestDto);
        commonResource.add(webMvcLinkBuilder.withRel("update-board"));

        return ResponseEntity.created(uri).body(commonResource);

    }


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
