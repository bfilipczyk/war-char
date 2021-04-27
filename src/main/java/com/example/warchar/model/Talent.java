package com.example.warchar.model;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;


@Data
@NoArgsConstructor
@Entity
public class Talent {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    @Lob
    private String description;

    @OneToMany(mappedBy = "talent")
    Set<CharacterTalents> characterTalentsSet;

}
