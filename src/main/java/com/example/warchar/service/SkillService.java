package com.example.warchar.service;

import com.example.warchar.model.Character;
import com.example.warchar.model.CharacterSkills;
import com.example.warchar.model.Skill;
import com.example.warchar.payload.RemoveCharacterDataRequest;
import com.example.warchar.payload.UpdateSkillTalent;
import com.example.warchar.repository.CharacterRepository;
import com.example.warchar.repository.CharacterSkillsRepository;
import com.example.warchar.repository.SkillRepository;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
@Service
public class SkillService {
    private final SkillRepository skillRepository;
    private final CharacterSkillsRepository characterSkillsRepository;
    private final CharacterRepository characterRepository;

    public void removeCharacterSkill(long characterSkillId) throws NotFoundException {
        CharacterSkills characterSkills = characterSkillsRepository.findById(characterSkillId)
                .orElseThrow(() -> new NotFoundException("Character skill with id: " + characterSkillId + " not found"));
        Character character = characterSkills.getCharacter();
        Skill skill = characterSkills.getSkill();

        Set<CharacterSkills> characterSkillsSet1 = character.getCharacterSkillsSet();
        characterSkillsSet1.remove(characterSkills);
        Set<CharacterSkills> characterSkillsSet2 = skill.getCharacterSkillsSet();
        characterSkillsSet2.remove(characterSkills);

        character.setCharacterSkillsSet(characterSkillsSet1);
        skill.setCharacterSkillsSet(characterSkillsSet2);

        skillRepository.save(skill);
        characterRepository.save(character);
        characterSkillsRepository.delete(characterSkills);
    }

    public List<Skill> getAllSkill() {
        return skillRepository.findAll();
    }

    public CharacterSkills updateCharacterSkill(UpdateSkillTalent request) throws NotFoundException {
        CharacterSkills characterSkills = characterSkillsRepository.findById(request.getId())
                .orElseThrow(() -> new NotFoundException("Character skill with id: " + request.getId() + " not found"));
        characterSkills.setAdvances(request.getValue());
        return characterSkillsRepository.save(characterSkills);

    }
}
