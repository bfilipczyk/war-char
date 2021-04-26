package com.example.warchar.repository;


import com.example.warchar.model.CharacterTalents;
import com.example.warchar.model.Weapon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WeaponRepository extends JpaRepository<Weapon, Long> {
}
