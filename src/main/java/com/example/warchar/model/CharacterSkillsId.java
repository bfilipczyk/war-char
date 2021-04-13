package com.example.warchar.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class CharacterSkillsId implements Serializable {
    @Column(name = "character_id")
    Long characterId;
    @Column(name = "skill_Id")
    Long skillId;

    public CharacterSkillsId() {
    }

    public CharacterSkillsId(Long characterId, Long skillId) {
        this.characterId = characterId;
        this.skillId = skillId;
    }

    public Long getCharacterId() {
        return characterId;
    }

    public void setCharacterId(Long characterId) {
        this.characterId = characterId;
    }

    public Long getSkillId() {
        return skillId;
    }

    public void setSkillId(Long skillId) {
        this.skillId = skillId;
    }
}
