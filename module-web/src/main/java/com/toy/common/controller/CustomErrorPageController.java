package com.toy.common.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

@Controller
@Slf4j
public class CustomErrorPageController implements ErrorController {
    private static final String ERROR_PAGE = "/error";

    @Override
    public String getErrorPath() {
        return ERROR_PAGE;
    }

    @RequestMapping("/error")
    public String errorPage(HttpServletRequest request) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        log.error("error 가 발생했습니다. : " + status.toString());

        return "exception";
    }

}
