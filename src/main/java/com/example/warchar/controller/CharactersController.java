package com.example.warchar.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CharactersController {

    @GetMapping(value = "/characters/{userId}")
    String characters(@PathVariable int userId)
    {
        return "Characters "+userId;
    }
    @GetMapping(value = "/character/{userId}/{characterId}")
    String getCharacter(@PathVariable int userId, @PathVariable int characterId){
        return "Character "+characterId+" "+userId;
    }



}
