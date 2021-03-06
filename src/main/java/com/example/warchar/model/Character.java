package com.example.warchar.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Getter
@Setter
@NoArgsConstructor
@Entity
public class Character {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String career;
    private String race;
    private int totalWounds;
    private int currentWounds;
    private int experience;
    private int fate;
    private int resilience;

    @OneToOne(orphanRemoval = true)
    private Characteristics characteristics;

    @OneToMany(mappedBy = "character",orphanRemoval = true)
    Set<CharacterSkills> characterSkillsSet;

    @OneToMany(mappedBy = "character",orphanRemoval = true)
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

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    @JsonBackReference(value = "users")
    private Users user;

}
