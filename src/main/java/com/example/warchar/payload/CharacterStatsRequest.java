package com.example.warchar.payload;

import lombok.Getter;

@Getter
public class CharacterStatsRequest {
    private String race;
    private String career;
    private int experience;
    private int totalWounds;
    private int currentWounds;
    private int fate;
    private int resilience;
}
