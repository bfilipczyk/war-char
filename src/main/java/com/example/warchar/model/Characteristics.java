package com.example.warchar.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


@Getter
@Setter
@NoArgsConstructor
@Entity
public class Characteristics {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToOne(mappedBy = "characteristics")
    @JsonIgnore
    private Character character;
    private int weaponSkill;
    private int ballisticSkill;
    private int strength;
    private int toughness;
    private int initiative;
    private int agility;
    private int dexterity;
    private int intelligence;
    private int willpower;
    private int fellowship;

}
