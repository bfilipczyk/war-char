package com.example.warchar.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

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

    @ManyToMany(mappedBy = "characterSet")
    private Set<Trapping> trappingSet = new HashSet<>();

    @ManyToMany(mappedBy = "characterSet")
    private Set<Weapon> weaponSet = new HashSet<>();

    @ManyToMany(mappedBy = "characterSet")
    private Set<Armor> armorSet = new HashSet<>();



    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users user;
    public Character() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Characteristics getCharacteristics() {
        return characteristics;
    }

    public void setCharacteristics(Characteristics characteristics) {
        this.characteristics = characteristics;
    }

    public Set<CharacterSkills> getCharacterSkillsSet() {
        return characterSkillsSet;
    }

    public void setCharacterSkillsSet(Set<CharacterSkills> characterSkillsSet) {
        this.characterSkillsSet = characterSkillsSet;
    }

    public Set<CharacterTalents> getCharacterTalentsSet() {
        return characterTalentsSet;
    }

    public void setCharacterTalentsSet(Set<CharacterTalents> characterTalentsSet) {
        this.characterTalentsSet = characterTalentsSet;
    }

    public Set<Trapping> getTrappingSet() {
        return trappingSet;
    }

    public void setTrappingSet(Set<Trapping> trappingSet) {
        this.trappingSet = trappingSet;
    }

    public Set<Armor> getArmorSet() {
        return armorSet;
    }

    public void setArmorSet(Set<Armor> armorSet) {
        this.armorSet = armorSet;
    }

    public Set<Weapon> getWeaponSet() {
        return weaponSet;
    }

    public void setWeaponSet(Set<Weapon> weaponSet) {
        this.weaponSet = weaponSet;
    }
}
