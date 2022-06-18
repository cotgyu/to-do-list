package com.toy.board.controller;


import com.toy.board.dto.BoardRequestDto;
import com.toy.board.dto.BoardValidator;
import com.toy.board.service.BoardService;
import com.toy.common.controller.IndexController;
import com.toy.config.auth.LoginUser;
import com.toy.config.auth.dto.SessionUser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping(value = "/api/board", produces = "application/hal+json;charset=utf8")
public class BoardApiController {

    private final BoardService boardService;

    private final BoardValidator boardValidator;

    @PostMapping
    public ResponseEntity addBoard(@RequestBody BoardRequestDto boardRequestDto, @LoginUser SessionUser user, Errors errors) {

        boardValidator.validate(boardRequestDto, errors);

        if (errors.hasErrors()) {
            log.debug("잘못된 요청입니다. error: " + errors.getFieldError());
            return ResponseEntity.badRequest().body(CollectionModel.of(errors.getAllErrors()));
        }

        Long result = boardService.save(boardRequestDto, user.getEmail());
        boardRequestDto.setId(result);

        WebMvcLinkBuilder webMvcLinkBuilder = linkTo(BoardApiController.class).slash(result);

        // 링크 제공
        EntityModel<BoardRequestDto> entityModel = EntityModel.of(boardRequestDto);
        // profile
        entityModel.add(linkTo(IndexController.class).slash("/docs/index.html#resources-add-board").withRel("profile"));
        // self
        entityModel.add(webMvcLinkBuilder.withSelfRel());
        // update
        entityModel.add(webMvcLinkBuilder.withRel("update-board"));

        return ResponseEntity.created(webMvcLinkBuilder.toUri()).body(entityModel);
    }

    @PutMapping("/{boardId}")
    public ResponseEntity updateBoard(@PathVariable Long boardId, @RequestBody BoardRequestDto boardRequestDto, @LoginUser SessionUser user, Errors errors) {

        boardValidator.validate(boardRequestDto, errors);

        if (errors.hasErrors()) {
            log.debug("잘못된 요청입니다. error: " + errors.getFieldError());
            return ResponseEntity.badRequest().body(CollectionModel.of(errors.getAllErrors()));
        }

        Long updateBoardId = boardService.updateBoard(boardId, boardRequestDto, user);
        boardRequestDto.setId(updateBoardId);

        if (updateBoardId == -1) {
            log.debug("해당 Board 수정 권한이 없습니다.");
            return new ResponseEntity(HttpStatus.UNAUTHORIZED);
        }

        WebMvcLinkBuilder webMvcLinkBuilder = linkTo(BoardController.class).slash(updateBoardId);

        // 링크 제공
        EntityModel<BoardRequestDto> entityModel = EntityModel.of(boardRequestDto);
        // profile
        entityModel.add(linkTo(IndexController.class).slash("/docs/index.html#resources-update-board").withRel("profile"));
        // self
        entityModel.add(webMvcLinkBuilder.withSelfRel());
        // get
        entityModel.add(webMvcLinkBuilder.withRel("get-board"));

        return ResponseEntity.created(webMvcLinkBuilder.toUri()).body(entityModel);
    }

}
