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

}
