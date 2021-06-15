package com.example.warchar.controller;

import com.example.warchar.model.CharacterSkills;
import com.example.warchar.model.CharacterTalents;
import com.example.warchar.model.Skill;
import com.example.warchar.model.Talent;
import com.example.warchar.payload.UpdateSkillTalent;
import com.example.warchar.service.TalentService;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/talent")
public class TalentController {
    private final TalentService talentService;

    @GetMapping()
    List<Talent> all() {
        return talentService.getAllTalent();
    }

    @DeleteMapping("/removeCharacterTalent/{characterTalentId}")
    void removeCharacterTalent(@PathVariable long characterTalentId) throws NotFoundException {
        talentService.removeCharacterTalent(characterTalentId);
    }

    @PatchMapping("/updateCharacterTalent")
    CharacterTalents updateCharacterTalent(@RequestBody UpdateSkillTalent request) throws NotFoundException {
        return talentService.updateCharacterTalent(request);
    }
}
