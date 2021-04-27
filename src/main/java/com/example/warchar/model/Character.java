package com.example.warchar.model;



import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Data
@NoArgsConstructor
@Entity
public class Character {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;

    @OneToOne
    private Characteristics characteristics;

    @OneToMany(mappedBy = "character")
    Set<CharacterSkills> characterSkillsSet;

    @OneToMany(mappedBy = "character")
    Set<CharacterTalents> characterTalentsSet;

    @ManyToMany
    @JoinTable(
            name = "character_trapping",
            joinColumns = @JoinColumn(name = "character_id"),
            inverseJoinColumns = @JoinColumn(name = "trapping_id"))
    private Set<Trapping> trappingSet = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "character_weapons",
            joinColumns = @JoinColumn(name = "character_id"),
            inverseJoinColumns = @JoinColumn(name = "weapon_id"))
    private Set<Weapon> weaponSet = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "character_armor",
            joinColumns = @JoinColumn(name = "character_id"),
            inverseJoinColumns = @JoinColumn(name = "armor_id"))
    private Set<Armor> armorSet = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users user;

}
