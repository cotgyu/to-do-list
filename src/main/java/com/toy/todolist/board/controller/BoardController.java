package com.toy.todolist.board.controller;

import com.toy.todolist.board.domain.Board;
import com.toy.todolist.board.domain.Topic;
import com.toy.todolist.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


@RequiredArgsConstructor
@Controller
@RequestMapping("/board")
public class BoardController {

    private final BoardService boardService;

    @RequestMapping("/{boardId}")
    public String allBoardContents(@PathVariable Long boardId, Model model){

        Board board = boardService.findAllContents(boardId);
        model.addAttribute("board", board);

        List<Topic> topics = board.getTopics();
        model.addAttribute("topics", topics);



        return "indexPage";
    }
}
