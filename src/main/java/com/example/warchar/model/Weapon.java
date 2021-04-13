package com.example.warchar.model;


import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Weapon {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String weaponGroup;
    private String dmg;

    @ManyToMany(mappedBy = "weaponSet")
    private Set<WeaponQuality> weaponQualitySet = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "character_weapon",
            joinColumns = @JoinColumn(name = "character_id"),
            inverseJoinColumns = @JoinColumn(name = "weapon_id")
    )
    private Set<Character> characterSet = new HashSet<>();

    public Weapon(){

    }

    public Weapon(Long id, String name, String weaponGroup, String dmg) {
        this.id = id;
        this.name = name;
        this.weaponGroup = weaponGroup;
        this.dmg = dmg;
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

    public String getWeaponGroup() {
        return weaponGroup;
    }

    public void setWeaponGroup(String weaponGroup) {
        this.weaponGroup = weaponGroup;
    }

    public String getDmg() {
        return dmg;
    }

    public void setDmg(String dmg) {
        this.dmg = dmg;
    }

    public Set<WeaponQuality> getWeaponQualitySet() {
        return weaponQualitySet;
    }

    public void setWeaponQualitySet(Set<WeaponQuality> weaponQualitySet) {
        this.weaponQualitySet = weaponQualitySet;
    }

    public Set<Character> getCharacterSet() {
        return characterSet;
    }

    public void setCharacterSet(Set<Character> characterSet) {
        this.characterSet = characterSet;
    }

}
