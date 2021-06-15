package com.example.warchar.controller;

import com.example.warchar.model.Armor;
import com.example.warchar.model.Trapping;
import com.example.warchar.payload.ArmorResponse;
import com.example.warchar.payload.RemoveCharacterDataRequest;
import com.example.warchar.service.ArmorService;
import com.example.warchar.service.TrappingService;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/trapping")
public class TrappingController {
    private final TrappingService trappingService;


    @GetMapping()
    List<Trapping> all() {
        return trappingService.getAllTrapping();
    }

    @PatchMapping("/removeCharacterTrapping")
    Trapping removeCharacterTrapping(@RequestBody RemoveCharacterDataRequest request) throws NotFoundException {
        return trappingService.removeCharacterTrapping(request);
    }
}
