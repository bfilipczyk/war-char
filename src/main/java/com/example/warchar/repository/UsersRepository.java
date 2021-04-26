package com.example.warchar.repository;


import com.example.warchar.model.CharacterTalents;
import com.example.warchar.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<Users, Long> {
}
