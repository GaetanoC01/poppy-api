package com.trafalino.poppy.repository;

import com.trafalino.poppy.dto.Wholesaler;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WholesalerRepository extends MongoRepository<Wholesaler, ObjectId> {
    Optional<Wholesaler> findWholesalerByNome(String name);
    Page<Optional<Wholesaler>> findWholesalerByNomeLike(String name, Pageable pageable);
    List<Wholesaler> deleteWholesalerByNome(String name);
}
