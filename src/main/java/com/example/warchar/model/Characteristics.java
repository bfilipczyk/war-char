package com.example.warchar.model;

import javax.persistence.*;

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

    public Characteristics() {
    }

    public Characteristics(Long id,
                           int weaponSkill,
                           int ballisticSkill,
                           int strength,
                           int toughness,
                           int initiative,
                           int agility,
                           int dexterity,
                           int intelligence,
                           int willpower,
                           int fellowship) {
        this.id = id;
        this.weaponSkill = weaponSkill;
        this.ballisticSkill = ballisticSkill;
        this.strength = strength;
        this.toughness = toughness;
        this.initiative = initiative;
        this.agility = agility;
        this.dexterity = dexterity;
        this.intelligence = intelligence;
        this.willpower = willpower;
        this.fellowship = fellowship;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getWeaponSkill() {
        return weaponSkill;
    }

    public void setWeaponSkill(int weaponSkill) {
        this.weaponSkill = weaponSkill;
    }

    public int getBallisticSkill() {
        return ballisticSkill;
    }

    public void setBallisticSkill(int ballisticSkill) {
        this.ballisticSkill = ballisticSkill;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getToughness() {
        return toughness;
    }

    public void setToughness(int toughness) {
        this.toughness = toughness;
    }

    public int getInitiative() {
        return initiative;
    }

    public void setInitiative(int initiative) {
        this.initiative = initiative;
    }

    public int getAgility() {
        return agility;
    }

    public void setAgility(int agility) {
        this.agility = agility;
    }

    public int getDexterity() {
        return dexterity;
    }

    public void setDexterity(int dexterity) {
        this.dexterity = dexterity;
    }

    public int getIntelligence() {
        return intelligence;
    }

    public void setIntelligence(int intelligence) {
        this.intelligence = intelligence;
    }

    public int getWillpower() {
        return willpower;
    }

    public void setWillpower(int willpower) {
        this.willpower = willpower;
    }

    public int getFellowship() {
        return fellowship;
    }

    public void setFellowship(int fellowship) {
        this.fellowship = fellowship;
    }
}
