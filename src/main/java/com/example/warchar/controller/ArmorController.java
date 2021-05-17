package com.example.warchar.controller;


import com.example.warchar.model.Armor;
import com.example.warchar.payload.ArmorResponse;
import com.example.warchar.service.ArmorService;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
