package com.example.warchar.repository;


import com.example.warchar.model.CharacterTalents;
import com.example.warchar.model.Trapping;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrappingRepository extends JpaRepository<Trapping, Long> {
}
