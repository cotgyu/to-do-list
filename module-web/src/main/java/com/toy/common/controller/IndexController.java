package com.toy.common.controller;


import com.toy.board.dto.BoardResponseDto;
import com.toy.board.service.BoardService;
import com.toy.config.auth.LoginUser;
import com.toy.config.auth.dto.SessionUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final BoardService boardService;

    @GetMapping("/")
    public String index(Model model, @LoginUser SessionUser user) {

        if (user != null) {
            model.addAttribute("userName", user.getName());
            model.addAttribute("user", user);

            List<BoardResponseDto> allBoardList = boardService.findAllBoardListByEmail(user.getEmail());

            model.addAttribute("boardList", allBoardList);
        }

        return "loginPage";
    }

    @GetMapping("/home")
    public String home(Model model, @LoginUser SessionUser user) {

        if (user != null) {
            model.addAttribute("userName", user.getName());
        }

        return "dist/index";
    }

}
