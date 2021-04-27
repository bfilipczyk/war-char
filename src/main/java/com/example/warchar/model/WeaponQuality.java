package com.example.warchar.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Data
@NoArgsConstructor
@Entity
public class WeaponQuality {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    @Lob
    private String description;

    @ManyToMany(mappedBy = "weaponQualitySet")
    private Set<Weapon> weaponSet = new HashSet<>();


}
