package com.example.warchar.model;


import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Skill {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    @Lob
    private String description;
    private String type;
    private String attribute;

    @OneToMany(mappedBy = "skill")
    Set<CharacterSkills> characterSkillsSet;

    public Skill(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAttribute() {
        return attribute;
    }

    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }

    public Set<CharacterSkills> getCharacterSkillsSet() {
        return characterSkillsSet;
    }

    public void setCharacterSkillsSet(Set<CharacterSkills> characterSkillsSet) {
        this.characterSkillsSet = characterSkillsSet;
    }
}
