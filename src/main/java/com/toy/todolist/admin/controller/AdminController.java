package com.toy.todolist.admin.controller;

import com.toy.todolist.config.auth.LoginUser;
import com.toy.todolist.config.auth.dto.SessionUser;
import com.toy.todolist.user.domain.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@Controller
@RequestMapping("/admin")
public class AdminController {

    @GetMapping("/index")
    public String home(Model model, @LoginUser SessionUser user){

        if(user.getRole() != Role.ADMIN){

            return "dist/404";
        }

        model.addAttribute("userName", user.getName());

        return "dist/index";
    }
}
