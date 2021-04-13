package com.example.warchar.model;

import javax.persistence.*;

@Entity
public class CharacterTalents {
    @EmbeddedId
    CharacterTalentsId id;

    @ManyToOne
    @MapsId("characterId")
    @JoinColumn(name = "character_id")
    Character character;

    @ManyToOne
    @MapsId("talentId")
    @JoinColumn(name = "talent_id")
    Talent talent;

    int advances;

    public CharacterTalents() {
    }

    public CharacterTalentsId getId() {
        return id;
    }

    public void setId(CharacterTalentsId id) {
        this.id = id;
    }

    public Character getCharacter() {
        return character;
    }

    public void setCharacter(Character character) {
        this.character = character;
    }

    public Talent getTalent() {
        return talent;
    }

    public void setTalent(Talent talent) {
        this.talent = talent;
    }

    public int getAdvances() {
        return advances;
    }

    public void setAdvances(int advances) {
        this.advances = advances;
    }
}
