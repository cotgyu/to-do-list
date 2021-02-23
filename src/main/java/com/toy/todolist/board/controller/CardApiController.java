package com.toy.todolist.board.controller;

import com.toy.todolist.board.dto.CardRequestDto;
import com.toy.todolist.board.dto.CardResponseDto;
import com.toy.todolist.board.dto.LabelRequestDto;
import com.toy.todolist.board.service.CardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/card")
public class CardApiController {

    private final CardService cardService;

    @PostMapping
    public ResponseEntity addCard(@RequestBody CardRequestDto cardRequestDto) {
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
    public ResponseEntity addLabel(@RequestBody LabelRequestDto labelRequestDto){
        Map<String, Object> resultMap = new HashMap<>();

        Long label_id = cardService.addLabel(labelRequestDto);

        resultMap.put("result", label_id);
        resultMap.put("resultMessage", "success");


        return new ResponseEntity<>(resultMap, HttpStatus.OK);
    }


}
