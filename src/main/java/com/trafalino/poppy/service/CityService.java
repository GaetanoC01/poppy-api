package com.trafalino.poppy.service;

import com.mongodb.client.result.UpdateResult;
import com.trafalino.poppy.dto.City;
import com.trafalino.poppy.dto.Province;
import com.trafalino.poppy.dto.Region;
import com.trafalino.poppy.repository.CityRepository;
import com.trafalino.poppy.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
        Region region = newCity.getRegione();
        Province province = newCity.getProvincia();
        newCity.setNome(
                StringUtils.capitalize(newCity.getNome())
        );

        region.setNome(
                StringUtils.capitalize(region.getNome())
        );
        province.setNome(
                StringUtils.capitalize(province.getNome())
        );
        newCity.setRegione(region);
        newCity.setProvincia(province);

        return cityRepository.save(newCity);
    }

    public Page<City> getAllCities(Pageable pageable){
        return cityRepository.findAll(pageable);
    }

    public Optional<City> getSingleCity(String name){
        return cityRepository.findCityByNome(StringUtils.capitalize(name));
    }

    public Page<Optional<City>> getCitiesLike(String name, Pageable pageable){
        return cityRepository.findCityByNomeLike(name, pageable);
    }

    public String updateSingleCity(
            String cityName,
            String field,
            String newValue
    ){
        Update updateDefinition = new Update();
        Query query = new Query().addCriteria(
                Criteria.where("nome").is(StringUtils.capitalize(cityName))
        );

        if (field.equals("regione")) {
            Region region = new Region();
            region.setNome(StringUtils.capitalize(newValue));
            updateDefinition = updateDefinition.set(field, region);
        }
        else if(field.equals("provincia")) {
            Province province = new Province();
            province.setNome(StringUtils.capitalize(newValue));
            updateDefinition = updateDefinition.set(field, province);
        } else {
            updateDefinition = updateDefinition.set(field, StringUtils.capitalize(newValue));
        }

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
