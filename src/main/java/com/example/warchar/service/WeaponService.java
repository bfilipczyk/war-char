package com.example.warchar.service;

import com.example.warchar.model.Character;
import com.example.warchar.model.Weapon;
import com.example.warchar.payload.RemoveCharacterWeaponRequest;
import com.example.warchar.payload.WeaponResponse;
import com.example.warchar.repository.*;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class WeaponService {
    private final WeaponRepository weaponRepository;
    private final WeaponQualityRepository weaponQualityRepository;
    private final CharacterRepository characterRepository;

    public Weapon removeCharacterWeapon(RemoveCharacterWeaponRequest request) throws NotFoundException {
        Character character = characterRepository.findById(request.getCharacterId()).orElseThrow(() -> new NotFoundException("Character with id: " + request.getCharacterId() + " not found"));
        Weapon weapon = weaponRepository.findById(request.getWeaponId()).orElseThrow(() -> new NotFoundException("Weapon with id: " + request.getWeaponId() + " not found"));
        Set<Character> weaponCharacters = weapon.getCharacterSet();
        Set<Weapon> charactersWeapons = character.getWeaponSet();
        weaponCharacters.remove(character);
        charactersWeapons.remove(weapon);

        character.setWeaponSet(charactersWeapons);
        weapon.setCharacterSet(weaponCharacters);

        characterRepository.save(character);
        return weaponRepository.save(weapon);
    }

    public List<WeaponResponse> getAllWeapon(){
        List<WeaponResponse> response = weaponRepository.findAll(Sort.by(Sort.Direction.ASC,"id")).stream().map(
                WeaponResponse::of).collect(Collectors.toList());

        return response;
    }
}
