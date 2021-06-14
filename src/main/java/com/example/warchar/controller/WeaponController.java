package com.example.warchar.controller;



import com.example.warchar.model.Weapon;
import com.example.warchar.payload.RemoveCharacterWeaponRequest;
import com.example.warchar.payload.WeaponResponse;
import com.example.warchar.service.WeaponService;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/weapon")
public class WeaponController {
    private final WeaponService weaponService;

    @GetMapping()
    List<WeaponResponse> all() {
        return weaponService.getAllWeapon();
    }

    @PatchMapping("/removeCharacterWeapon")
    Weapon removeCharacterWeapon(@RequestBody RemoveCharacterWeaponRequest request) throws NotFoundException {
        return weaponService.removeCharacterWeapon(request);
    }
}
