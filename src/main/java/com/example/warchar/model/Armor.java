package com.example.warchar.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Armor {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String location;
    private int armorPoints;

    @ManyToMany(mappedBy = "armorSet")
    private Set<ArmorQuality> armorQualitySet = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "character_armor",
            joinColumns = @JoinColumn(name = "character_id"),
            inverseJoinColumns = @JoinColumn(name = "armor_id")
    )
    private Set<Character> characterSet = new HashSet<>();

    public Armor () {

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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getArmorPoints() {
        return armorPoints;
    }

    public void setArmorPoints(int armorPoints) {
        this.armorPoints = armorPoints;
    }

    public Set<ArmorQuality> getArmorQualitySet() {
        return armorQualitySet;
    }

    public void setArmorQualitySet(Set<ArmorQuality> armorQualitySet) {
        this.armorQualitySet = armorQualitySet;
    }

    public Set<Character> getCharacterSet() {
        return characterSet;
    }

    public void setCharacterSet(Set<Character> characterSet) {
        this.characterSet = characterSet;
    }
}
