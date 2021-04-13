package com.example.warchar.model;

import javax.persistence.*;

@Entity
public class CharacterSkills {
    @EmbeddedId
    CharacterSkillsId id;

    @ManyToOne
    @MapsId("characterId")
    @JoinColumn(name = "character_id")
    Character character;

    @ManyToOne
    @MapsId("skillId")
    @JoinColumn(name = "skill_id")
    Skill skill;

    int advances;

    public CharacterSkills() {
    }

    public CharacterSkillsId getId() {
        return id;
    }

    public void setId(CharacterSkillsId id) {
        this.id = id;
    }

    public Character getCharacter() {
        return character;
    }

    public void setCharacter(Character character) {
        this.character = character;
    }

    public Skill getSkill() {
        return skill;
    }

    public void setSkill(Skill skill) {
        this.skill = skill;
    }

    public int getAdvances() {
        return advances;
    }

    public void setAdvances(int advances) {
        this.advances = advances;
    }
}
