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

    @GetMapping("/{id}")
    public ResponseEntity findCard(@PathVariable Long id){
        Map<String, Object> resultMap = new HashMap<>();

        CardResponseDto card = cardService.findCard(id);

        resultMap.put("result", card);
        resultMap.put("resultMessage", "success");

        return new ResponseEntity<>(resultMap, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity updateCard(@PathVariable Long id, @RequestBody CardRequestDto cardRequestDto){
        Map<String, Object> resultMap = new HashMap<>();

        cardService.updateCard(id, cardRequestDto);

        resultMap.put("result", id);
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

    @PutMapping("/label/{id}")
    public ResponseEntity updateLabel(@PathVariable Long id, @RequestBody LabelRequestDto labelRequestDto){
        Map<String, Object> resultMap = new HashMap<>();

        cardService.updateLabel(id, labelRequestDto);

        resultMap.put("result", id);
        resultMap.put("resultMessage", "success");


        return new ResponseEntity<>(resultMap, HttpStatus.OK);
    }


    @PostMapping("/label/register")
    public ResponseEntity registerLabel(@RequestBody LabelRequestDto labelRequestDto){
        Map<String, Object> resultMap = new HashMap<>();

        Long label_id = cardService.registerLabel(labelRequestDto);

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



}
