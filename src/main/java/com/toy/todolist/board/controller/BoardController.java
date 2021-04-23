package com.toy.todolist.board.controller;

import com.toy.todolist.board.domain.Board;
import com.toy.todolist.board.domain.Topic;
import com.toy.todolist.board.dto.BoardResponseDto;
import com.toy.todolist.board.dto.TopicResponseDto;
import com.toy.todolist.board.service.BoardService;
import com.toy.todolist.config.auth.LoginUser;
import com.toy.todolist.config.auth.dto.SessionUser;
import com.toy.todolist.user.domain.Role;
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
    public String allBoardContents(@PathVariable Long boardId, Model model, @LoginUser SessionUser user){

        BoardResponseDto board = boardService.findAllContents(boardId);
        model.addAttribute("board", board);

        List<TopicResponseDto> topics = board.getTopics();
        model.addAttribute("topics", topics);

        model.addAttribute("user", user);



        return "indexPage";
    }
}
