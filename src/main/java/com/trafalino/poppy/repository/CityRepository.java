package com.trafalino.poppy.repository;

import com.trafalino.poppy.dto.City;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CityRepository extends MongoRepository<City, ObjectId> {
    Optional<City> findCityByNome(String name);
}
