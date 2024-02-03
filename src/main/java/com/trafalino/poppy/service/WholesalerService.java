package com.trafalino.poppy.service;

import com.mongodb.client.result.UpdateResult;
import com.trafalino.poppy.dto.Wholesaler;
import com.trafalino.poppy.repository.WholesalerRepository;
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
public class WholesalerService {

    @Autowired
    private WholesalerRepository wholesalerRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    public Wholesaler save(Wholesaler newWholesaler){
        newWholesaler.setNome(StringUtils.capitalize(newWholesaler.getNome()));
        return wholesalerRepository.save(newWholesaler);
    }

    public List<Wholesaler> getAllWholesalers() {
        return wholesalerRepository.findAll();
    }

    public Optional<Wholesaler> getSingleWholesaler(String name) {
        return wholesalerRepository.findWholesalerByNome(StringUtils.capitalize(name));
    }

    public String updateSingleWholesaler(
            String wholesalerName,
            String field,
            String newValue
    ) {
        Update updateDefinition = null;
        Query query = new Query().addCriteria(
                Criteria.where("nome").is(StringUtils.capitalize(wholesalerName))
        );

        if (field.equals("nome")) {
            updateDefinition = new Update().set(field, StringUtils.capitalize(newValue));
        }
        else if (field.equals("scontistica")) {
            double newValueConverted = Double.parseDouble(newValue);
            updateDefinition = new Update().set(field, newValueConverted);
        }

        if(updateDefinition!=null){
            UpdateResult updateResult = mongoTemplate.updateFirst(
                    query,
                    updateDefinition,
                    Wholesaler.class
            );

            return updateResult.toString();
        }

        return null;
    }

    public List<Wholesaler> deleteWholesaler(String name){
        return wholesalerRepository.deleteWholesalerByNome(StringUtils.capitalize(name));
    }
}
