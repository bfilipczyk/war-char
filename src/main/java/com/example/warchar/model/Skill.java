package com.example.warchar.model;



import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
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

}
