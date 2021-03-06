package com.example.warchar.controller;



import com.example.warchar.model.Weapon;
import com.example.warchar.model.WeaponQuality;
import com.example.warchar.payload.CharacterDataChangeRequest;
import com.example.warchar.service.WeaponService;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/weapon")
public class WeaponController {
    private final WeaponService weaponService;

    @GetMapping()
    List<Weapon> all() {
        return weaponService.getAllWeapon();
    }

    @GetMapping("/quality")
    List<WeaponQuality> getAllWeaponQuality() {
        return weaponService.getAllWeaponQuality();
    }

    @PostMapping("/addCharacterWeapon")
    Weapon addCharacterWeapon(@RequestBody CharacterDataChangeRequest request) throws NotFoundException {
        return weaponService.addCharacterWeapon(request);
    }

    @PatchMapping("/removeCharacterWeapon")
    Weapon removeCharacterWeapon(@RequestBody CharacterDataChangeRequest request) throws NotFoundException {
        return weaponService.removeCharacterWeapon(request);
    }
}
