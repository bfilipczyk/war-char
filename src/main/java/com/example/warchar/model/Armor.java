package com.example.warchar.model;


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
    @JsonIgnore
    private Set<Character> characterSet = new HashSet<>();

}
