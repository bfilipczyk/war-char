package com.example.warchar.service;


import com.example.warchar.model.Armor;
import com.example.warchar.model.ArmorQuality;
import com.example.warchar.model.Character;
import com.example.warchar.payload.CharacterDataChangeRequest;
import com.example.warchar.repository.ArmorQualityRepository;
import com.example.warchar.repository.ArmorRepository;
import com.example.warchar.repository.CharacterRepository;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;


@RequiredArgsConstructor
@Service
public class ArmorService {


    private final ArmorRepository armorRepository;
    private final ArmorQualityRepository armorQualityRepository;
    private final CharacterRepository characterRepository;

    public List<Armor> getAllArmor(){

        return armorRepository.findAll(Sort.by(Sort.Direction.ASC,"id"));

    }

    public List<ArmorQuality> getAllArmorQuality(){
        return armorQualityRepository.findAll(Sort.by(Sort.Direction.ASC,"name"));
    }


    public Armor removeCharacterArmor(CharacterDataChangeRequest request) throws NotFoundException {
        Character character = characterRepository.findById(request.getCharacterId()).orElseThrow(() -> new NotFoundException("Character with id: " + request.getCharacterId() + " not found"));
        Armor armor = armorRepository.findById(request.getDataId()).orElseThrow(() -> new NotFoundException("Armor with id: " + request.getDataId() + " not found"));
        Set<Character> armorCharacters = armor.getCharacterSet();
        Set<Armor> charactersArmor = character.getArmorSet();
        armorCharacters.remove(character);
        charactersArmor.remove(armor);

        character.setArmorSet(charactersArmor);
        armor.setCharacterSet(armorCharacters);

        characterRepository.save(character);
        return armorRepository.save(armor);
    }
    public Armor addCharacterArmor(CharacterDataChangeRequest request) throws NotFoundException {
        Character character = characterRepository.findById(request.getCharacterId())
                .orElseThrow(() -> new NotFoundException("Character with id: " + request.getCharacterId() + " not found"));
        Armor armor = armorRepository.findById(request.getDataId())
                .orElseThrow(() -> new NotFoundException("Armor with id: " + request.getDataId() + " not found"));
        Set<Character> armorCharacters = armor.getCharacterSet();
        Set<Armor> characterArmor = character.getArmorSet();
        armorCharacters.add(character);
        characterArmor.add(armor);

        characterRepository.save(character);
        return armorRepository.save(armor);
    }
}
