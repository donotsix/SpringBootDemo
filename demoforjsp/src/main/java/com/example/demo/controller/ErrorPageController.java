package com.example.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("error")
public class ErrorPageController implements ErrorController {

    private  static final Logger LOGGER = LoggerFactory.getLogger(ErrorPageController.class);

    @Override
    public String getErrorPath() {
        return "error/error";
    }

    public String error() {
        LOGGER.warn("访问error页面");
        return this.getErrorPath();
    }
}

