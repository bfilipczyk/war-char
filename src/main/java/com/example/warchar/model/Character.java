package com.example.warchar.model;



import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @OneToOne
    private Characteristics characteristics;

    @OneToMany(mappedBy = "character")
    @JsonIgnore
    Set<CharacterSkills> characterSkillsSet;

    @OneToMany(mappedBy = "character")
    @JsonIgnore
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
