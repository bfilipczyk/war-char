package com.example.warchar.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class CharacterTalentsId implements Serializable {
    @Column(name = "character_id")
    Long characterId;
    @Column(name = "talent_Id")
    Long talentId;

    public CharacterTalentsId() {
    }

    public CharacterTalentsId(Long characterId, Long talentId) {
        this.characterId = characterId;
        this.talentId = talentId;
    }

    public Long getCharacterId() {
        return characterId;
    }

    public void setCharacterId(Long characterId) {
        this.characterId = characterId;
    }

    public Long getTalentId() {
        return talentId;
    }

    public void setTalentId(Long talentId) {
        this.talentId = talentId;
    }
}
