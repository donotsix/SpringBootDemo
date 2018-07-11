package com.futurecode.ajax_server;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author :lvweihao@outlook.com
 * @description:
 * @date :create in 20:02 18.5.21
 * @modify by :
 */
@RestController
@RequestMapping("/ajax")
public class AjaxController {

    @GetMapping("/get1")
    private ResultBean get1() {
        System.out.println("get1 start");
        return new ResultBean("get1 ok");
    }

}
