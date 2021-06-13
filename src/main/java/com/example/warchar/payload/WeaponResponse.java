package com.example.warchar.payload;


import com.example.warchar.model.Weapon;
import lombok.Data;

@Data
public class WeaponResponse {
    private final Long id;

    private final String name;
    private final String weaponGroup;
    private final String dmg;

    public static WeaponResponse of(Weapon weapon){
        return new WeaponResponse(weapon.getId(),
                weapon.getName(),
                weapon.getWeaponGroup(),
                weapon.getDmg());
    }
}
