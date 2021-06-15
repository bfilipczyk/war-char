package com.example.warchar.service;

import com.example.warchar.model.Character;
import com.example.warchar.model.CharacterSkills;
import com.example.warchar.model.Skill;
import com.example.warchar.payload.CharacterDataChangeRequest;
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

    public CharacterSkills addCharacterSkill(CharacterDataChangeRequest request) throws NotFoundException {
        if(characterSkillsRepository.existsById(request.getDataId()))
        {
            return null;
        }
        CharacterSkills characterSkills = new CharacterSkills();
        Character character = characterRepository.findById(request.getCharacterId())
                .orElseThrow(() -> new NotFoundException("Character with id: " + request.getCharacterId() + " not found"));
        Skill skill = skillRepository.findById(request.getDataId())
                .orElseThrow(() -> new NotFoundException("Skill with id: " + request.getDataId() + " not found"));

        characterSkills.setAdvances(0);
        characterSkills.setCharacter(character);
        characterSkills.setSkill(skill);

        characterSkills = characterSkillsRepository.save(characterSkills);

        Set<CharacterSkills> characterSkillsSet1 = character.getCharacterSkillsSet();
        characterSkillsSet1.add(characterSkills);
        Set<CharacterSkills> characterSkillsSet2 = skill.getCharacterSkillsSet();
        characterSkillsSet2.add(characterSkills);

        character.setCharacterSkillsSet(characterSkillsSet1);
        skill.setCharacterSkillsSet(characterSkillsSet2);

        skillRepository.save(skill);
        characterRepository.save(character);

        return characterSkills;
    }
}
