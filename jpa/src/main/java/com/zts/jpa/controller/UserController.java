package com.zts.jpa.controller;

import com.zts.jpa.dao.UserDao;
import com.zts.jpa.entity.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserDao userDao;

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public List<User> list(){
        return userDao.findAll();
    }
}
