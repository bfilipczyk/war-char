package com.example.warchar.model;


import javax.persistence.*;
import java.util.Set;

@Entity
public class Talent {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String description;

    @OneToMany(mappedBy = "talent")
    Set<CharacterTalents> characterTalentsSet;

    public Talent () {

    }
    public Talent(Long id, String description) {
       this.id = id;
       this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<CharacterTalents> getCharacterTalentsSet() {
        return characterTalentsSet;
    }

    public void setCharacterTalentsSet(Set<CharacterTalents> characterTalentsSet) {
        this.characterTalentsSet = characterTalentsSet;
    }
}
