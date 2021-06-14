package com.example.warchar.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Getter
@Setter
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
    @JsonIgnore
    private Set<Weapon> weaponSet = new HashSet<>();


}
