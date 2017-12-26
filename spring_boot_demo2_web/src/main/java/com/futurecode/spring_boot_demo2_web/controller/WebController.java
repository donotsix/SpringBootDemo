package com.futurecode.spring_boot_demo2_web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/web")
public class WebController {

    private  static final  Logger LOGGER = LoggerFactory.getLogger(WebController.class);

    @RequestMapping("index")
    public String index (ModelMap map) {

        LOGGER.warn("request index in webcontroller");
        // System.out.println("request index in webcontroller");

        map.put("title" , "freemarker demo");

        return "index";

    }

}
