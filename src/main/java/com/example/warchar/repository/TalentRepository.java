package com.example.warchar.repository;


import com.example.warchar.model.CharacterTalents;
import com.example.warchar.model.Talent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TalentRepository extends JpaRepository<Talent, Long> {
}
