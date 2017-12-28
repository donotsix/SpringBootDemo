package com.futurecode.springbootfilter.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/web")
public class WebAController {

    @RequestMapping("index")
    public String index() {
        return "this is index";
    }

}
