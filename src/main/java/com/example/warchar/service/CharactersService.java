package com.example.warchar.service;

import com.example.warchar.model.Character;
import com.example.warchar.model.Characteristics;
import com.example.warchar.payload.CharactersResponse;
import com.example.warchar.payload.NewCharacterRequest;
import com.example.warchar.repository.CharacterRepository;
import com.example.warchar.repository.CharacteristicsRepository;
import com.example.warchar.repository.UsersRepository;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CharactersService {
    private final CharacterRepository characterRepository;
    private final CharacteristicsRepository characteristicsRepository;
    private final UsersRepository usersRepository;

    public List<CharactersResponse> getAllCharactersById(Long userId){
        return characterRepository.findAll(Sort.by(Sort.Direction.DESC,"name"))
                .stream().map(CharactersResponse::of).collect(Collectors.toList());
    }

    public Character getCharacterById(long characterId) throws NotFoundException {

        Character character = characterRepository.findById(characterId)
                .orElseThrow(() -> new NotFoundException("Character with id: " + characterId + " not found"));
        return character;
    }


    public Character newCharacter(NewCharacterRequest newCharacterRequest, long userId) {
        Characteristics characteristics = new Characteristics();
        Character character = new Character();

        characteristics.setWeaponSkill(0);
        characteristics.setBallisticSkill(0);
        characteristics.setStrength(0);
        characteristics.setToughness(0);
        characteristics.setInitiative(0);
        characteristics.setAgility(0);
        characteristics.setDexterity(0);
        characteristics.setIntelligence(0);
        characteristics.setWillpower(0);
        characteristics.setFellowship(0);

        characteristics = characteristicsRepository.save(characteristics);
        character.setName(newCharacterRequest.getName());
        character.setUser(usersRepository.findById(userId).get());
        character.setCharacteristics(characteristics);
        character.setCharacterSkillsSet(null);
        character.setCharacterTalentsSet(null);
        character.setArmorSet(null);
        character.setWeaponSet(null);
        character.setTrappingSet(null);

        return characterRepository.save(character);
    }
}
