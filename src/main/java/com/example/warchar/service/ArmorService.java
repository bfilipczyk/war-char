package com.example.warchar.service;


import com.example.warchar.model.Armor;
import com.example.warchar.payload.ArmorResponse;
import com.example.warchar.repository.ArmorQualityRepository;
import com.example.warchar.repository.ArmorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@Service
public class ArmorService {


    private final ArmorRepository armorRepository;
    private final ArmorQualityRepository armorQualityRepository;

    public List<ArmorResponse> getAllArmor(){
        List<ArmorResponse> response = armorRepository.findAll(Sort.by(Sort.Direction.ASC,"id")).stream().map(
                ArmorResponse::of).collect(Collectors.toList());

        return response;

    }

    public Optional<Armor> getArmorById(long id){return armorRepository.findById(id);}


}
