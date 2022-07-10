package com.toy.board.controller;


import com.toy.board.dto.*;
import com.toy.board.service.CardService;
import com.toy.common.controller.IndexController;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping(value = "/api/card")
public class CardApiController {

    private final CardService cardService;

    private final CardValidator cardValidator;

    private final LabelValidator labelValidator;

    private final CardLabelValidator cardLabelValidator;

    @PostMapping
    public ResponseEntity saveCard(@RequestBody CardRequestDto cardRequestDto, Errors errors) {

        cardValidator.validate(cardRequestDto, errors);

        if (errors.hasErrors()) {
            log.debug("잘못된 요청입니다. error: " + errors.getFieldError());
            return ResponseEntity.badRequest().body(CollectionModel.of(errors.getAllErrors()));
        }

        Long result = cardService.saveCard(cardRequestDto);

        WebMvcLinkBuilder webMvcLinkBuilder = linkTo(CardApiController.class).slash(result);
        EntityModel<CardRequestDto> entityModel = EntityModel.of(cardRequestDto);
        entityModel.add(linkTo(IndexController.class).slash("/docs/index.html#resources-add-card").withRel("profile"));
        entityModel.add(webMvcLinkBuilder.withSelfRel());
        entityModel.add(webMvcLinkBuilder.withRel("update-card"));

        return ResponseEntity.created(webMvcLinkBuilder.toUri()).body(entityModel);
    }

    @GetMapping("/{cardId}")
    public ResponseEntity findCard(@PathVariable Long cardId) {
        Map<String, Object> resultMap = new HashMap<>();
        CardResponseDto card = cardService.findCard(cardId);

        resultMap.put("result", card);
        resultMap.put("resultMessage", "success");

        return new ResponseEntity<>(resultMap, HttpStatus.OK);
    }

    @PutMapping("/{cardId}")
    public ResponseEntity updateCard(@PathVariable Long cardId,
                                     @RequestBody CardRequestDto cardRequestDto, Errors errors) {

        cardValidator.validate(cardRequestDto, errors);

        if (errors.hasErrors()) {
            log.debug("잘못된 요청입니다. error: " + errors.getFieldError());
            return ResponseEntity.badRequest().body(CollectionModel.of(errors.getAllErrors()));
        }

        Long result = cardService.updateCard(cardId, cardRequestDto);

        WebMvcLinkBuilder webMvcLinkBuilder = linkTo(CardApiController.class).slash(result);

        EntityModel<CardRequestDto> entityModel = EntityModel.of(cardRequestDto);
        entityModel.add(linkTo(IndexController.class).slash("/docs/index.html#resources-update-card").withRel("profile"));
        entityModel.add(webMvcLinkBuilder.withSelfRel());
        entityModel.add(webMvcLinkBuilder.withRel("select-card"));

        return ResponseEntity.created(webMvcLinkBuilder.toUri()).body(entityModel);
    }

    @PostMapping("/label")
    public ResponseEntity saveLabel(@RequestBody LabelRequestDto labelRequestDto, Errors errors) {

        labelValidator.validate(labelRequestDto, errors);

        if (errors.hasErrors()) {
            log.debug("잘못된 요청입니다. error: " + errors.getFieldError());
            return ResponseEntity.badRequest().body(CollectionModel.of(errors.getAllErrors()));
        }

        Long labelId = cardService.saveLabel(labelRequestDto);

        WebMvcLinkBuilder webMvcLinkBuilder = linkTo(CardApiController.class).slash(labelId);

        EntityModel<LabelRequestDto> entityModel = EntityModel.of(labelRequestDto);
        entityModel.add(webMvcLinkBuilder.withSelfRel());
        entityModel.add(webMvcLinkBuilder.withRel("select-label"));

        return ResponseEntity.created(webMvcLinkBuilder.toUri()).body(entityModel);
    }

    @GetMapping("/label")
    public ResponseEntity findAllLabels() {
        Map<String, Object> resultMap = new HashMap<>();

        List<LabelResponseDto> allLabels = cardService.findAllLabels();

        resultMap.put("result", allLabels);
        resultMap.put("resultMessage", "success");

        return new ResponseEntity<>(resultMap, HttpStatus.OK);
    }

    @GetMapping("/label/{cardId}")
    public ResponseEntity findCardLabels(@PathVariable Long cardId) {
        Map<String, Object> resultMap = new HashMap<>();

        List<CardLabelQueryDto> cardLabels = cardService.findCardLabels(cardId);

        resultMap.put("result", cardLabels);
        resultMap.put("resultMessage", "success");

        return new ResponseEntity<>(resultMap, HttpStatus.OK);
    }

    @PutMapping("/label/cardLabel")
    public ResponseEntity updateCardLabels(@RequestBody CardLabelRequestDto cardLabelRequestDto, Errors errors) {

        cardLabelValidator.validate(cardLabelRequestDto, errors);

        if (errors.hasErrors()) {
            log.debug("잘못된 요청입니다. error: " + errors.getFieldError());
            return ResponseEntity.badRequest().body(CollectionModel.of(errors.getAllErrors()));
        }

        cardService.updateCardLabel(cardLabelRequestDto);

        WebMvcLinkBuilder webMvcLinkBuilder = linkTo(CardApiController.class);

        EntityModel<CardLabelRequestDto> entityModel = EntityModel.of(cardLabelRequestDto);
        entityModel.add(webMvcLinkBuilder.withSelfRel());

        return ResponseEntity.created(webMvcLinkBuilder.toUri()).body(entityModel);
    }

    @PutMapping("/label/{labelId}")
    public ResponseEntity updateLabel(@PathVariable Long labelId,
                                      @RequestBody LabelRequestDto labelRequestDto, Errors errors) {
        labelValidator.validate(labelRequestDto, errors);

        if (errors.hasErrors()) {
            log.debug("잘못된 요청입니다. error: " + errors.getFieldError());
            return ResponseEntity.badRequest().body(CollectionModel.of(errors.getAllErrors()));
        }

        cardService.updateLabel(labelId, labelRequestDto);

        WebMvcLinkBuilder webMvcLinkBuilder = linkTo(CardApiController.class).slash(labelId);

        EntityModel<LabelRequestDto> entityModel = EntityModel.of(labelRequestDto);
        entityModel.add(webMvcLinkBuilder.withSelfRel());
        entityModel.add(webMvcLinkBuilder.withRel("select-label"));

        return ResponseEntity.created(webMvcLinkBuilder.toUri()).body(entityModel);
    }


    @PostMapping("/label/register")
    public ResponseEntity registerLabel(@RequestBody CardLabelRequestDto cardLabelRequestDto, Errors errors) {
        cardLabelValidator.validate(cardLabelRequestDto, errors);

        if (errors.hasErrors()) {
            log.debug("잘못된 요청입니다. error: " + errors.getFieldError());
            return ResponseEntity.badRequest().body(CollectionModel.of(errors.getAllErrors()));
        }

        Long labelId = cardService.registerLabel(cardLabelRequestDto);

        WebMvcLinkBuilder webMvcLinkBuilder = linkTo(CardApiController.class).slash(labelId);

        EntityModel<CardLabelRequestDto> entityModel = EntityModel.of(cardLabelRequestDto);
        entityModel.add(webMvcLinkBuilder.withSelfRel());

        return ResponseEntity.created(webMvcLinkBuilder.toUri()).body(entityModel);
    }

    @PostMapping("/checkList")
    public ResponseEntity addCheckList(@RequestBody CheckListRequestDto checkListRequestDto) {
        // TODO validate 를 다른 방법으로 고려

        Long checkListId = cardService.saveCheckList(checkListRequestDto);

        WebMvcLinkBuilder webMvcLinkBuilder = linkTo(CardApiController.class).slash(checkListId);

        EntityModel<CheckListRequestDto> entityModel = EntityModel.of(checkListRequestDto);
        entityModel.add(webMvcLinkBuilder.withSelfRel());
        return ResponseEntity.created(webMvcLinkBuilder.toUri()).body(entityModel);
    }

    @PutMapping("/checkList/{checkListId}")
    public ResponseEntity updateCheckList(@PathVariable Long checkListId, @RequestBody CheckListRequestDto checkListRequestDto) {
        cardService.updateCheckList(checkListId, checkListRequestDto);

        WebMvcLinkBuilder webMvcLinkBuilder = linkTo(CardApiController.class).slash(checkListId);

        EntityModel<CheckListRequestDto> entityModel = EntityModel.of(checkListRequestDto);
        entityModel.add(webMvcLinkBuilder.withSelfRel());
        return ResponseEntity.created(webMvcLinkBuilder.toUri()).body(entityModel);
    }

    @PostMapping("/checkList/checkItem")
    public ResponseEntity addCheckItem(@RequestBody CheckItemRequestDto checkItemRequestDto) {
        Long checkItemId = cardService.addCheckItem(checkItemRequestDto);

        WebMvcLinkBuilder webMvcLinkBuilder = linkTo(CardApiController.class).slash(checkItemId);

        EntityModel<CheckItemRequestDto> entityModel = EntityModel.of(checkItemRequestDto);
        entityModel.add(webMvcLinkBuilder.withSelfRel());
        return ResponseEntity.created(webMvcLinkBuilder.toUri()).body(entityModel);
    }

    @PutMapping("/checkList/checkItem/{checkItemId}")
    public ResponseEntity updateCheckItem(@PathVariable Long checkItemId, @RequestBody CheckItemRequestDto checkItemRequestDto) {
        cardService.updateCheckItem(checkItemId, checkItemRequestDto);

        WebMvcLinkBuilder webMvcLinkBuilder = linkTo(CardApiController.class).slash(checkItemId);

        EntityModel<CheckItemRequestDto> entityModel = EntityModel.of(checkItemRequestDto);
        entityModel.add(webMvcLinkBuilder.withSelfRel());
        return ResponseEntity.created(webMvcLinkBuilder.toUri()).body(entityModel);
    }
}
