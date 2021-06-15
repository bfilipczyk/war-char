package com.example.warchar.controller;


import com.example.warchar.model.Armor;
import com.example.warchar.model.Weapon;
import com.example.warchar.payload.ArmorResponse;
import com.example.warchar.payload.RemoveCharacterDataRequest;
import com.example.warchar.service.ArmorService;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;

import org.springframework.security.access.prepost.PreAuthorize;
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

    @GetMapping("/{id}")
    Optional<Armor> armorById(@PathVariable long id){return armorService.getArmorById(id);}

    @PatchMapping("/removeCharacterArmor")
    Armor removeCharacterArmor(@RequestBody RemoveCharacterDataRequest request) throws NotFoundException {
        return armorService.removeCharacterArmor(request);
    }
}
