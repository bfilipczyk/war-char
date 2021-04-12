package com.example.warchar.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @RequestMapping(value = "/user/{userId}")
    public String user(@PathVariable int userId)
    {
        return "User "+userId;
    }
}
