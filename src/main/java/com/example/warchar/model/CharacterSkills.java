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
public class CharacterSkills {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @ManyToOne
    @JoinColumn(name = "character_id")
    @JsonIgnore
    Character character;

    @ManyToOne
    @JoinColumn(name = "skill_id")
    Skill skill;

    int advances;
}
