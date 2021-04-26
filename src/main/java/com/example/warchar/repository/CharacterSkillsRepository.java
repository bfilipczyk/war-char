package com.example.warchar.repository;


import com.example.warchar.model.Character;
import com.example.warchar.model.CharacterSkills;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CharacterSkillsRepository extends JpaRepository<CharacterSkills, Long> {
}
