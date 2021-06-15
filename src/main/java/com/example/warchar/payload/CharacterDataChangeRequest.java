package com.example.warchar.payload;

import lombok.Getter;

@Getter
public class CharacterDataChangeRequest {
    private long characterId;
    private long dataId;
}
