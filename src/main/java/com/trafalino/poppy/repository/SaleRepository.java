package com.trafalino.poppy.repository;

import com.trafalino.poppy.dto.Sale;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

//TODO: find by multiple fields?
@Repository
public interface SaleRepository extends MongoRepository<Sale, ObjectId> {
    void deleteById(String id);
}
