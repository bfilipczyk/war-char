package com.example.warchar.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class WeaponQuality {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String descrition;

    public WeaponQuality(Long id, String descrition) {
        this.id = id;
        this.descrition = descrition;
    }


    public WeaponQuality() {

    }

    @ManyToMany
    @JoinTable(
            name = "weapon_qualities",
            joinColumns = @JoinColumn(name = "weapon_id"),
            inverseJoinColumns = @JoinColumn(name = "quality_id")
    )
    private Set<Weapon> weaponSet = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescrition() {
        return descrition;
    }

    public void setDescrition(String descrition) {
        this.descrition = descrition;
    }

    public Set<Weapon> getWeaponSet() {
        return weaponSet;
    }

    public void setWeaponSet(Set<Weapon> weaponSet) {
        this.weaponSet = weaponSet;
    }
}
