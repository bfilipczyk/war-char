package com.example.warchar.controller;



import com.example.warchar.payload.WeaponResponse;
import com.example.warchar.service.WeaponService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
