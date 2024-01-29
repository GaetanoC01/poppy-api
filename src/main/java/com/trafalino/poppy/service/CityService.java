package com.trafalino.poppy.service;

import com.trafalino.poppy.dto.City;
import com.trafalino.poppy.repository.CityRepository;
import com.trafalino.poppy.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CityService {
    @Autowired
    private CityRepository cityRepository;


    public City save(City newCity) {
        return cityRepository.save(newCity);
    }

    public Optional<City> singleCity(String name){
        return cityRepository.findCityByNome(StringUtils.capitalize(name));
    }

    public List<City> deleteCity(String name){
        return cityRepository.deleteCityByNome(StringUtils.capitalize(name));
    }
}
