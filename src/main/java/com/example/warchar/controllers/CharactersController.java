package com.example.warchar.controllers;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CharactersController {

    @RequestMapping(value = "/characters/{userId}")
    public String characters(@PathVariable int userId){
        return "Characters "+userId;
    }

    @RequestMapping(value = "/characters/{userId}/{characterId}")
    public String character(@PathVariable int userId, @PathVariable int characterId){
        return "Character "+userId+" "+characterId;
    }


}
