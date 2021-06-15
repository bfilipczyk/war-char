package com.example.warchar.service;

import com.example.warchar.model.Character;
import com.example.warchar.model.Trapping;
import com.example.warchar.model.Weapon;
import com.example.warchar.payload.RemoveCharacterDataRequest;
import com.example.warchar.repository.CharacterRepository;
import com.example.warchar.repository.TrappingRepository;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
@Service
public class TrappingService {
    private final TrappingRepository trappingRepository;
    private final CharacterRepository characterRepository;

    public List<Trapping> getAllTrapping() {
        return trappingRepository.findAll();
    }

    public Trapping removeCharacterTrapping(RemoveCharacterDataRequest request) throws NotFoundException {
        Character character = characterRepository.findById(request.getCharacterId()).orElseThrow(() -> new NotFoundException("Character with id: " + request.getCharacterId() + " not found"));
        Trapping trapping = trappingRepository.findById(request.getDataId()).orElseThrow(() -> new NotFoundException("Trapping with id: " + request.getDataId() + " not found"));
        Set<Character> trappingCharacters = trapping.getCharacterSet();
        Set<Trapping> charactersTrapping = character.getTrappingSet();
        trappingCharacters.remove(character);
        charactersTrapping.remove(trapping);

        character.setTrappingSet(charactersTrapping);
        trapping.setCharacterSet(trappingCharacters);

        characterRepository.save(character);
        return trappingRepository.save(trapping);
    }
}
