package com.futurecode.spring_boot_demo1.controller;

import com.futurecode.spring_boot_demo1.bean.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
public class DemoController {

    @GetMapping("/index/index")
    public String index () {
        return "hello world , my new world";
    }

    @Value("${futurecode.secret}")
    private String secret;

    @Value("${futurecode.intran}")
    private String intran;

    @Value("${futurecode.desc}")
    private String desc;

    @GetMapping("/index/get")
    public Map<String , Object> get(@RequestParam String method , @RequestParam Integer param) {
        Map<String, Object> map = new HashMap<>();
        map.put("method" , method);
        map.put("param" , param);
        map.put("secret" , secret);
        map.put("intran" , intran);
        map.put("desc" , desc);
        map.put("date" , new Date());
        System.out.println(map.get("date"));
        return  map;
    }

    @GetMapping("index/getUser")
    public User getUser() {
        User user = new User();
        user.setName("洁大宝");
        user.setAge(24);
        user.setHabbit("气鼓鼓");
        return user;
    }

    @GetMapping("index/getUserFromPath/{name}/{age}/{habbit}")
    public User getUserFromPath(@PathVariable String name , @PathVariable Integer age , @PathVariable String habbit) {
        User user = new User();
        user.setName(name);
        user.setAge(age);
        user.setHabbit(habbit);
        // 默认日期格式为时间戳 , 毫秒数 , 可以修改
        user.setDate(new Date());
        return user;
    }

}
