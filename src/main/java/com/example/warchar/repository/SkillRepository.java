package com.example.warchar.repository;


import com.example.warchar.model.CharacterTalents;
import com.example.warchar.model.Skill;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SkillRepository extends JpaRepository<Skill, Long> {
}
