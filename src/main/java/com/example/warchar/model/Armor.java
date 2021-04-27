package com.example.warchar.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity
public class Armor {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String location;
    private int armorPoints;

    @ManyToMany
    @JoinTable(
            name = "armor_qualities",
            joinColumns = @JoinColumn(name = "armor_id"),
            inverseJoinColumns = @JoinColumn(name = "quality_id"))
    private Set<ArmorQuality> armorQualitySet = new HashSet<>();

    @ManyToMany(mappedBy = "armorSet")
    private Set<Character> characterSet = new HashSet<>();

}
