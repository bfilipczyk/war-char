package com.example.warchar.model;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Data
@NoArgsConstructor
@Entity
public class Weapon {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String weaponGroup;
    private String dmg;


    @ManyToMany
    @JoinTable(
            name = "weapon_qualities",
            joinColumns = @JoinColumn(name = "weapon_id"),
            inverseJoinColumns = @JoinColumn(name = "quality_id"))
    private Set<WeaponQuality> weaponQualitySet = new HashSet<>();

    @ManyToMany(mappedBy = "weaponSet")
    private Set<Character> characterSet = new HashSet<>();


}
