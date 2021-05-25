package com.toy.admin.controller;


import com.toy.admin.service.AdminService;
import com.toy.config.auth.LoginUser;
import com.toy.config.auth.dto.SessionUser;
import com.toy.user.domain.Role;
import com.toy.user.dto.UserResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("/admin")
public class AdminController {

    private final AdminService adminService;

    @GetMapping("/index")
    public String home(Model model, @LoginUser SessionUser user){

        if(user.getRole() != Role.ADMIN){

            return "dist/404";
        }

        List<UserResponseDto> allUserInfo = adminService.getAllUserInfo();

        model.addAttribute("userName", user.getName());
        model.addAttribute("allUserInfo", allUserInfo);

        return "adminPage";
    }
}
