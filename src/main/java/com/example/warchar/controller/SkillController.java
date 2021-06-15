package com.example.warchar.controller;

import com.example.warchar.model.Armor;
import com.example.warchar.model.CharacterSkills;
import com.example.warchar.model.Skill;
import com.example.warchar.payload.ArmorResponse;
import com.example.warchar.payload.RemoveCharacterDataRequest;
import com.example.warchar.payload.UpdateSkillTalent;
import com.example.warchar.service.SkillService;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/skill")
public class SkillController {
    private final SkillService skillService;

    @GetMapping()
    List<Skill> all() {
        return skillService.getAllSkill();
    }

    @DeleteMapping("/removeCharacterSkill/{characterSkillId}")
    void removeCharacterSkill(@PathVariable long characterSkillId) throws NotFoundException {
        skillService.removeCharacterSkill(characterSkillId);
    }

    @PatchMapping("/updateCharacterSkill")
    CharacterSkills updateCharacterSkill(@RequestBody UpdateSkillTalent request) throws NotFoundException {
        return skillService.updateCharacterSkill(request);
    }
}
