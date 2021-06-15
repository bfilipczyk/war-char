package com.example.warchar.service;

import com.example.warchar.model.*;
import com.example.warchar.model.Character;
import com.example.warchar.payload.CharacterDataChangeRequest;
import com.example.warchar.payload.UpdateSkillTalent;
import com.example.warchar.repository.CharacterRepository;
import com.example.warchar.repository.CharacterTalentsRepository;
import com.example.warchar.repository.TalentRepository;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
@Service
public class TalentService {
    private final TalentRepository talentRepository;
    private final CharacterTalentsRepository characterTalentsRepository;
    private final CharacterRepository characterRepository;

    public void removeCharacterTalent(long characterTalentId) throws NotFoundException {
        CharacterTalents characterTalents = characterTalentsRepository.findById(characterTalentId)
                .orElseThrow(() -> new NotFoundException("Character talent with id: " + characterTalentId + " not found"));
        Character character = characterTalents.getCharacter();
        Talent talent = characterTalents.getTalent();

        Set<CharacterTalents> characterTalentsSet1 = character.getCharacterTalentsSet();
        characterTalentsSet1.remove(characterTalents);
        Set<CharacterTalents> characterTalentsSet2 = talent.getCharacterTalentsSet();
        characterTalentsSet2.remove(characterTalents);

        character.setCharacterTalentsSet(characterTalentsSet1);
        talent.setCharacterTalentsSet(characterTalentsSet2);

        talentRepository.save(talent);
        characterRepository.save(character);
        characterTalentsRepository.delete(characterTalents);
    }

    public List<Talent> getAllTalent() {
        return talentRepository.findAll();
    }

    public CharacterTalents updateCharacterTalent(UpdateSkillTalent request) throws NotFoundException {
        CharacterTalents characterTalents = characterTalentsRepository.findById(request.getId())
                .orElseThrow(() -> new NotFoundException("Character talent with id: " + request.getId() + " not found"));
        characterTalents.setAdvances(request.getValue());
        return characterTalentsRepository.save(characterTalents);
    }

    public CharacterTalents addCharacterTalent(CharacterDataChangeRequest request) throws NotFoundException {
        if(characterTalentsRepository.existsById(request.getDataId()))
        {
            return null;
        }
        CharacterTalents characterTalents = new CharacterTalents();
        Character character = characterRepository.findById(request.getCharacterId())
                .orElseThrow(() -> new NotFoundException("Character with id: " + request.getCharacterId() + " not found"));
        Talent talent = talentRepository.findById(request.getDataId())
                .orElseThrow(() -> new NotFoundException("talent with id: " + request.getDataId() + " not found"));

        characterTalents.setAdvances(1);
        characterTalents.setCharacter(character);
        characterTalents.setTalent(talent);

        characterTalents = characterTalentsRepository.save(characterTalents);

        Set<CharacterTalents> characterTalentsSet1 = character.getCharacterTalentsSet();
        characterTalentsSet1.add(characterTalents);
        Set<CharacterTalents> characterTalentsSet2 = talent.getCharacterTalentsSet();
        characterTalentsSet2.add(characterTalents);

        character.setCharacterTalentsSet(characterTalentsSet1);
        talent.setCharacterTalentsSet(characterTalentsSet2);

        talentRepository.save(talent);
        characterRepository.save(character);

        return characterTalents;
    }
}
