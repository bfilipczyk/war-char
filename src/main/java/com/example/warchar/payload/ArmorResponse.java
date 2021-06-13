package com.example.warchar.payload;

import com.example.warchar.model.Armor;
import lombok.Data;


@Data
public class ArmorResponse {
    private final Long id;

    private final String name;
    private final String location;
    private final int armorPoints;

    public static ArmorResponse of(Armor armor){
        return new ArmorResponse(armor.getId(),
                armor.getName(),
                armor.getLocation(),
                armor.getArmorPoints());
    }


}
