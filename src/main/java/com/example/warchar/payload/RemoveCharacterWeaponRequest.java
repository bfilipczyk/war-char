package com.example.warchar.payload;

import lombok.Getter;

@Getter
public class RemoveCharacterWeaponRequest {
    private long characterId;
    private long weaponId;
}
