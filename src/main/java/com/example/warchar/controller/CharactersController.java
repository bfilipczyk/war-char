package com.example.warchar.controller;


import com.example.warchar.model.Character;
import com.example.warchar.model.Characteristics;
import com.example.warchar.payload.CharacterStatsRequest;
import com.example.warchar.payload.CharacteristicsRequest;
import com.example.warchar.payload.CharactersResponse;
import com.example.warchar.payload.NewCharacterRequest;
import com.example.warchar.service.CharactersService;
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

    @PutMapping(value = "/updateCharacteristics/{characteristicsId}")
    Characteristics updateCharacteristics(@RequestBody CharacteristicsRequest characteristicsRequest, @PathVariable long characteristicsId) throws NotFoundException {
        return charactersService.updateCharacteristics(characteristicsRequest,characteristicsId);
    }

    @PatchMapping(value = "/updateCharacterStats/{characterId}")
    Character updateCharacterStats(@RequestBody CharacterStatsRequest request, @PathVariable long characterId) throws NotFoundException {
        System.out.println("Test");
        return charactersService.updateCharacterStats(request,characterId);
    }

    @DeleteMapping("/removeCharacter/{characterId}")
    void removeCharacter(@PathVariable long characterId) {
        charactersService.removeCharacter(characterId);
    }


}
