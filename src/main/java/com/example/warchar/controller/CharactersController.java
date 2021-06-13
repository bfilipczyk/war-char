package com.example.warchar.controller;


import com.example.warchar.model.Character;
import com.example.warchar.model.Characteristics;
import com.example.warchar.payload.CharactersResponse;
import com.example.warchar.payload.NewCharacterRequest;
import com.example.warchar.service.CharactersService;
import com.sun.istack.NotNull;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/characters")
public class CharactersController {

    private final CharactersService charactersService;

    @GetMapping(value = "/{userId}")
    List<CharactersResponse> getUserCharacters(@PathVariable long userId){
        return charactersService.getAllCharactersById(userId);
    }
    @GetMapping(value = "/character/{characterId}")
    Character getCharacter(@PathVariable long characterId) throws NotFoundException {
        return charactersService.getCharacterById(characterId);
    }

    @PostMapping(value = "/newCharacter/{userId}")
    Character newCharacter(@RequestBody  NewCharacterRequest newCharacterRequest, @PathVariable long userId) {
        return charactersService.newCharacter(newCharacterRequest,userId);
    }




}
