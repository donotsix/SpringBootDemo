package com.futurecode.springbooterror.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {

    @RequestMapping("index")
    public String index() {
        return "this is index";
    }

    @RequestMapping("in")
    public String in() {
        throw new RuntimeException("测试异常");
    }

    @RequestMapping("ind")
    public String ind() throws Exception {
        throw new Exception("测试异常");
    }

}
