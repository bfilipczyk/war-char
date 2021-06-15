package com.example.warchar.payload;

import lombok.Getter;

@Getter
public class RemoveCharacterDataRequest {
    private long characterId;
    private long dataId;
}
