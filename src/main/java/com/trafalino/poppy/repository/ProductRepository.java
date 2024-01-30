package com.trafalino.poppy.repository;

import com.trafalino.poppy.dto.Product;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends MongoRepository<Product, ObjectId> {
    Optional<Product> findProductByNome(String name);
    List<Product> deleteProductByNome(String name);
}
