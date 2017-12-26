package com.futurecode.springbooterror.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

// @Controller
@RequestMapping("error")
public class BaseErrorController implements ErrorController {

    private static final Logger LOGGER = LoggerFactory.getLogger(BaseErrorController.class);

    @Override
    public String getErrorPath() {
        return "error";
    }

    @RequestMapping
    public String error() {
        LOGGER.error("访问error页面");
        return this.getErrorPath();
    }
}
