package com.example.warchar.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Data
@NoArgsConstructor
@Entity
public class CharacterTalents {
    @Id
    Long id;

    @ManyToOne
    @JoinColumn(name = "character_id")
    Character character;

    @ManyToOne
    @JoinColumn(name = "talent_id")
    Talent talent;

    int advances;

}
