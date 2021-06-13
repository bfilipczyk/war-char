package com.example.warchar.payload;

import com.example.warchar.model.Character;
import lombok.Data;

@Data
public class CharactersResponse {
    private final long id;

    private final String name;

    public static CharactersResponse of(Character character) {
        return new CharactersResponse(character.getId(), character.getName());
    }
}
