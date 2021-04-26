package com.example.warchar.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class ArmorQuality {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    @Lob
    private String description;

    @ManyToMany
    @JoinTable(
            name = "armor_qualities",
            joinColumns = @JoinColumn(name = "armor_id"),
            inverseJoinColumns = @JoinColumn(name = "quality_id")
    )
    private Set<Armor> armorSet = new HashSet<>();

    public ArmorQuality() {

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String descrition) {
        this.description = descrition;
    }

    public Set<Armor> getArmorSet() {
        return armorSet;
    }

    public void setArmorSet(Set<Armor> armorSet) {
        this.armorSet = armorSet;
    }


}
