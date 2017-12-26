package com.futurecode.springbootcors1.controller;

import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("api")
public class ApiController {

    //@RequestMapping(value="get" , method = {RequestMethod.POST, RequestMethod.GET})
    @PostMapping("get")
    @CrossOrigin(origins = "http://localhost:8081" , methods = RequestMethod.POST)
    public Map<String , Object> get(@RequestParam("name" )String name) {
        Map<String , Object> map = new HashMap<>();
        map.put("name" , name);
        map.put("path" , "api/get");
        return map;
    }

}
