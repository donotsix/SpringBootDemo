package com.futurecode.springbootmybatis2.controller;

import com.futurecode.springbootmybatis2.bean.RoncooUser;
import com.futurecode.springbootmybatis2.mapper.RoncooUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("api")
public class ApiController {

    @Autowired
    private RoncooUserMapper roncooUserMapper;

    @RequestMapping(value = "insert" , method = RequestMethod.GET)
    public RoncooUser insert(@PathVariable("name") String name) {
        RoncooUser roncooUser = new RoncooUser();
        roncooUser.setName(name);
        roncooUser.setCreateTime(new Date());
        int insert = roncooUserMapper.insert(roncooUser);
        System.out.println(insert);
        return roncooUser;
    }

    @RequestMapping(value = "select" , method = RequestMethod.GET)
    public RoncooUser select() {
        RoncooUser roncooUser = roncooUserMapper.selectByPrimaryKey(2);
        System.out.println(roncooUser);
        return roncooUser;
    }

    // ABCD * 9 = DCBA 求 ABCD
    public static void main(String[] args) {
        // version 1
        /*for (int a = 1 ; a <= 9 ; a ++) {
            for (int b = 0 ; b <= 9 ; b ++) {
                for (int c = 0 ; c <= 9 ; c ++) {
                    for (int d = 1 ; d <= 9 ; d ++) {
                        int i = a * 1000 + b * 100 + c * 10 + d;
                        int j = d * 1000 + c * 100 + b * 10 + a;
                        if (i * 9 == j) {
                            System.out.println("" + a + b + c + d);
                        }
                    }
                }
            }
        }*/

        // version2 *9 依然是四位数说明a是1 d是9
        /*for (int b = 0 ; b <= 9 ; b ++) {
            for (int c = 0 ; c <= 9 ; c ++) {
                int i = 1 * 1000 + b * 100 + c * 10 + 9;
                int j = 9 * 1000 + c * 100 + b * 10 + 1;
                if (i * 9 == j) {
                    System.out.println("" + 1 + b + c + 9);
                }
            }
        }*/

        // version3 已知d是9 *9会进8 即 (c*9+8) / 10 = c , b = (c*9+8) % 10
        // c进位 + b * 9 <= 9 可以知道b是0
        for (int c = 0 ; c <= 9 ; c ++) {
            int b = (c * 9 + 8) % 10;
            if ((c*9+8) / 10 == c && b * 9 + c <= 9) {
                System.out.println("" + 1 + b + c + 9);
            }
        }

        // version4 c进位 + b * 9 <= 9 可以知道b是0
        for (int c = 0 ; c <= 9 ; c ++) {
            if ((c * 9 + 8) % 10 == 0) {
                System.out.println("" + 1 + 0 + c + 9);
            }
        }
    }



}
