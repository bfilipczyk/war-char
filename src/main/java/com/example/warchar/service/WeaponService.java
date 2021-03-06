package com.example.warchar.service;

import com.example.warchar.model.Character;
import com.example.warchar.model.Weapon;
import com.example.warchar.model.WeaponQuality;
import com.example.warchar.payload.CharacterDataChangeRequest;
import com.example.warchar.repository.*;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
@Service
public class WeaponService {
    private final WeaponRepository weaponRepository;
    private final WeaponQualityRepository weaponQualityRepository;
    private final CharacterRepository characterRepository;

    public Weapon removeCharacterWeapon(CharacterDataChangeRequest request) throws NotFoundException {
        Character character = characterRepository.findById(request.getCharacterId())
                .orElseThrow(() -> new NotFoundException("Character with id: " + request.getCharacterId() + " not found"));
        Weapon weapon = weaponRepository.findById(request.getDataId())
                .orElseThrow(() -> new NotFoundException("Weapon with id: " + request.getDataId() + " not found"));
        Set<Character> weaponCharacters = weapon.getCharacterSet();
        Set<Weapon> charactersWeapons = character.getWeaponSet();
        weaponCharacters.remove(character);
        charactersWeapons.remove(weapon);

        character.setWeaponSet(charactersWeapons);
        weapon.setCharacterSet(weaponCharacters);

        characterRepository.save(character);
        return weaponRepository.save(weapon);
    }

    public List<Weapon> getAllWeapon(){

        return weaponRepository.findAll(Sort.by(Sort.Direction.ASC,"id"));
    }

    public List<WeaponQuality> getAllWeaponQuality(){
        return weaponQualityRepository.findAll(Sort.by(Sort.Direction.ASC,"name"));
    }

    public Weapon addCharacterWeapon(CharacterDataChangeRequest request) throws NotFoundException {
        Character character = characterRepository.findById(request.getCharacterId())
                .orElseThrow(() -> new NotFoundException("Character with id: " + request.getCharacterId() + " not found"));
        Weapon weapon = weaponRepository.findById(request.getDataId())
                .orElseThrow(() -> new NotFoundException("Weapon with id: " + request.getDataId() + " not found"));
        Set<Character> weaponCharacters = weapon.getCharacterSet();
        Set<Weapon> charactersWeapons = character.getWeaponSet();
        weaponCharacters.add(character);
        charactersWeapons.add(weapon);
        characterRepository.save(character);
        return weaponRepository.save(weapon);
    }
}
