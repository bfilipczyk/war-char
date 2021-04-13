package com.example.warchar.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class ArmorQuality {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String descrition;

    public ArmorQuality(Long id, String descrition) {
        this.id = id;
        this.descrition = descrition;
    }


    public ArmorQuality() {

    }

    @ManyToMany
    @JoinTable(
            name = "armor_qualities",
            joinColumns = @JoinColumn(name = "armor_id"),
            inverseJoinColumns = @JoinColumn(name = "quality_id")
    )
    private Set<Armor> armorSet = new HashSet<>();

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

    public Set<Armor> getArmorSet() {
        return armorSet;
    }

    public void setArmorSet(Set<Armor> armorSet) {
        this.armorSet = armorSet;
    }


}
