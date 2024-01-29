package com.trafalino.poppy.service;

import com.mongodb.client.result.UpdateResult;
import com.trafalino.poppy.dto.City;
import com.trafalino.poppy.repository.CityRepository;
import com.trafalino.poppy.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CityService {

    @Autowired
    private CityRepository cityRepository;
    @Autowired
    private MongoTemplate mongoTemplate;

    public City save(City newCity) {
        return cityRepository.save(newCity);
    }

    public List<City> getAllCities(){
        return cityRepository.findAll();
    }

    public Optional<City> getSingleCity(String name){
        return cityRepository.findCityByNome(StringUtils.capitalize(name));
    }

    public String updateSingleCity(
            String cityName,
            String field,
            String newValue
    ){
        Query query = new Query().addCriteria(
                Criteria.where("nome").is(StringUtils.capitalize(cityName))
        );
        Update updateDefinition = new Update().set(field, newValue);
        UpdateResult updateResult = mongoTemplate.updateFirst(
                query,
                updateDefinition,
                City.class
        );

        return updateResult.toString();
    }

    public String addToArrayCity(
            String cityName,
            String field,
            String value
    ) {
        Query query = new Query().addCriteria(
                Criteria.where("nome").is(StringUtils.capitalize(cityName))
        );

        Update updateDefinition = new Update().push(field).value(value);
        UpdateResult updateResult = mongoTemplate.updateFirst(
                query,
                updateDefinition,
                City.class
        );

        return updateResult.toString();
    }

    public String removeFromArrayCity(
            String cityName,
            String field,
            String value
    ) {
        Query query = new Query().addCriteria(
                Criteria.where("nome").is(StringUtils.capitalize(cityName))
        );

        Update updateDefinition = new Update().pull(field, value);
        UpdateResult updateResult = mongoTemplate.updateFirst(
                query,
                updateDefinition,
                City.class
        );

        return updateResult.toString();
    }

    public List<City> deleteCity(String name){
        return cityRepository.deleteCityByNome(StringUtils.capitalize(name));
    }
}
