package com.toy.board.controller;

import com.toy.board.dto.BoardResponseDto;
import com.toy.board.dto.TopicResponseDto;
import com.toy.board.service.BoardService;
import com.toy.config.auth.LoginUser;
import com.toy.config.auth.dto.SessionUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


@RequiredArgsConstructor
@Controller
@RequestMapping("/board")
public class BoardController {

    private final BoardService boardService;

    @RequestMapping("/{boardId}")
    public String allBoardContents(@PathVariable Long boardId, Model model, @LoginUser SessionUser user) {

        BoardResponseDto board = boardService.findAllContents(boardId);

        if (board.getUser().getId() != user.getId()) {
            return "dist/401";
        }
        model.addAttribute("board", board);

        List<TopicResponseDto> topics = board.getTopics();
        model.addAttribute("topics", topics);

        model.addAttribute("user", user);


        return "indexPage";
    }
}
