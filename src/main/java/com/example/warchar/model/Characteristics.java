package com.example.warchar.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Data
@NoArgsConstructor
@Entity
public class Characteristics {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
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
