package com.toy.todolist.board.controller;

import com.toy.todolist.board.dto.*;
import com.toy.todolist.board.service.CardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/card")
public class CardApiController {

    private final CardService cardService;

    @PostMapping
    public ResponseEntity saveCard(@RequestBody CardRequestDto cardRequestDto) {
        Map<String, Object> resultMap = new HashMap<>();

        Long result = cardService.saveCard(cardRequestDto);

        resultMap.put("result", result);
        resultMap.put("resultMessage", "success");

        return new ResponseEntity<>(resultMap, HttpStatus.OK);

    }

    @GetMapping("/{cardId}")
    public ResponseEntity findCard(@PathVariable Long cardId){
        Map<String, Object> resultMap = new HashMap<>();

        CardResponseDto card = cardService.findCard(cardId);

        resultMap.put("result", card);
        resultMap.put("resultMessage", "success");

        return new ResponseEntity<>(resultMap, HttpStatus.OK);
    }

    @PutMapping("/{cardId}")
    public ResponseEntity updateCard(@PathVariable Long cardId, @RequestBody CardRequestDto cardRequestDto){
        Map<String, Object> resultMap = new HashMap<>();

        cardService.updateCard(cardId, cardRequestDto);

        resultMap.put("result", cardId);
        resultMap.put("resultMessage", "success");

        return new ResponseEntity<>(resultMap, HttpStatus.OK);
    }

    @PostMapping("/label")
    public ResponseEntity saveLabel(@RequestBody LabelRequestDto labelRequestDto){
        Map<String, Object> resultMap = new HashMap<>();

        Long label_id = cardService.saveLabel(labelRequestDto);

        resultMap.put("result", label_id);
        resultMap.put("resultMessage", "success");


        return new ResponseEntity<>(resultMap, HttpStatus.OK);
    }

    @GetMapping("/label")
    public ResponseEntity findAllLabels(){
        Map<String, Object> resultMap = new HashMap<>();

        List<LabelResponseDto> allLabels = cardService.findAllLabels();

        resultMap.put("result", allLabels);
        resultMap.put("resultMessage", "success");


        return new ResponseEntity<>(resultMap, HttpStatus.OK);
    }

    @PutMapping("/label/{labelId}")
    public ResponseEntity updateLabel(@PathVariable Long labelId, @RequestBody LabelRequestDto labelRequestDto){
        Map<String, Object> resultMap = new HashMap<>();

        cardService.updateLabel(labelId, labelRequestDto);

        resultMap.put("result", labelId);
        resultMap.put("resultMessage", "success");


        return new ResponseEntity<>(resultMap, HttpStatus.OK);
    }


    @PostMapping("/label/register")
    public ResponseEntity registerLabel(@RequestBody CardLabelRequestDto cardLabelRequestDto){
        Map<String, Object> resultMap = new HashMap<>();

        Long label_id = cardService.registerLabel(cardLabelRequestDto);

        resultMap.put("result", label_id);
        resultMap.put("resultMessage", "success");


        return new ResponseEntity<>(resultMap, HttpStatus.OK);
    }

    @PostMapping("/checkList")
    public ResponseEntity addCheckList(@RequestBody CheckListRequestDto checkListRequestDto){
        Map<String, Object> resultMap = new HashMap<>();

        Long checkList_id = cardService.saveCheckList(checkListRequestDto);

        resultMap.put("result", checkList_id);
        resultMap.put("resultMessage", "success");

        return new ResponseEntity<>(resultMap, HttpStatus.OK);
    }

    @PutMapping("/checkList/{checkListId}")
    public ResponseEntity updateCheckList(@PathVariable Long checkListId, @RequestBody CheckListRequestDto checkListRequestDto){
        Map<String, Object> resultMap = new HashMap<>();

        cardService.updateCheckList(checkListId, checkListRequestDto);

        resultMap.put("result", checkListId);
        resultMap.put("resultMessage", "success");

        return new ResponseEntity<>(resultMap, HttpStatus.OK);
    }

    @PostMapping("/checkList/checkItem")
    public ResponseEntity addCheckItem(@RequestBody CheckItemRequestDto checkItemRequestDto){
        Map<String, Object> resultMap = new HashMap<>();

        Long checkItemId = cardService.addCheckItem(checkItemRequestDto);

        resultMap.put("result", checkItemId);
        resultMap.put("resultMessage", "success");

        return new ResponseEntity<>(resultMap, HttpStatus.OK);

    }

    @PutMapping("/checkList/checkItem/{checkItemId}")
    public ResponseEntity updateCheckItem(@PathVariable Long checkItemId, @RequestBody CheckItemRequestDto checkItemRequestDto){
        Map<String, Object> resultMap = new HashMap<>();

        cardService.updateCheckItem(checkItemId, checkItemRequestDto);

        resultMap.put("result", checkItemId);
        resultMap.put("resultMessage", "success");

        return new ResponseEntity<>(resultMap, HttpStatus.OK);

    }



}
