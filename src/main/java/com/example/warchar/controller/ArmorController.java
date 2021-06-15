package com.example.warchar.controller;


import com.example.warchar.model.Armor;
import com.example.warchar.model.Weapon;
import com.example.warchar.payload.ArmorResponse;
import com.example.warchar.payload.CharacterDataChangeRequest;
import com.example.warchar.service.ArmorService;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RequiredArgsConstructor
@RestController
@RequestMapping("/api/armor")
public class ArmorController {


    private final ArmorService armorService;


    @GetMapping()
    List<ArmorResponse> all() {
        return armorService.getAllArmor();
    }

    @PostMapping("/addCharacterArmor")
    Armor addCharacterArmor(@RequestBody CharacterDataChangeRequest request) throws NotFoundException {
        return armorService.addCharacterArmor(request);
    }

    @PatchMapping("/removeCharacterArmor")
    Armor removeCharacterArmor(@RequestBody CharacterDataChangeRequest request) throws NotFoundException {
        return armorService.removeCharacterArmor(request);
    }
}
