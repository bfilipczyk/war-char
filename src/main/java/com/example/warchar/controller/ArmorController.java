package com.example.warchar.controller;


import com.example.warchar.model.Armor;
import com.example.warchar.model.ArmorQuality;
import com.example.warchar.model.WeaponQuality;
import com.example.warchar.payload.CharacterDataChangeRequest;
import com.example.warchar.service.ArmorService;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequiredArgsConstructor
@RestController
@RequestMapping("/api/armor")
public class ArmorController {


    private final ArmorService armorService;


    @GetMapping()
    List<Armor> all() {
        return armorService.getAllArmor();
    }

    @GetMapping("/quality")
    List<ArmorQuality> getAllArmorQuality() {
        return armorService.getAllArmorQuality();
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
