package com.trafalino.poppy.repository;

import com.trafalino.poppy.dto.Sale;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

//TODO: find by multiple fields?
@Repository
public interface SaleRepository extends MongoRepository<Sale, ObjectId> {
    Optional<List<Sale>> findSaleByAnno(int year);
    Optional<List<Sale>> findSaleByMese(String month);
    Optional<List<Sale>> findSaleByGrossista(String wholesaler);
    Optional<List<Sale>> findSaleByCitta(String city);
    Optional<List<Sale>> findSaleByCap(String cap);
    Optional<List<Sale>> findSaleByRegione(String region);
    Optional<List<Sale>> findSaleByProvincia(String province);
    Optional<List<Sale>> findSaleByProdotto(String product);

    void deleteById(String id);
}
