package com.trafalino.poppy.service;

import com.trafalino.poppy.dto.City;
import com.trafalino.poppy.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CityService {
    @Autowired
    private CityRepository cityRepository;

    public Optional<City> singleCity(String name){
        name = name.substring(0, 1).toUpperCase() + name.substring(1);
        return cityRepository.findCityByNome(name);
    }
}
