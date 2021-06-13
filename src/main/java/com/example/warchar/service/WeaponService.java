package com.example.warchar.service;

import com.example.warchar.model.Armor;
import com.example.warchar.model.Weapon;
import com.example.warchar.payload.ArmorResponse;
import com.example.warchar.payload.WeaponResponse;
import com.example.warchar.repository.ArmorQualityRepository;
import com.example.warchar.repository.ArmorRepository;
import com.example.warchar.repository.WeaponQualityRepository;
import com.example.warchar.repository.WeaponRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class WeaponService {
    private final WeaponRepository weaponRepository;
    private final WeaponQualityRepository weaponQualityRepository;

    public List<WeaponResponse> getAllWeapon(){
        List<WeaponResponse> response = weaponRepository.findAll(Sort.by(Sort.Direction.ASC,"id")).stream().map(
                WeaponResponse::of).collect(Collectors.toList());

        return response;

    }

    public Optional<Weapon> getWeaponById(long id){return weaponRepository.findById(id);}
}
