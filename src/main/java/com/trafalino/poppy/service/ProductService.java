package com.trafalino.poppy.service;


import com.mongodb.client.result.UpdateResult;
import com.trafalino.poppy.dto.Product;
import com.trafalino.poppy.repository.ProductRepository;
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
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    public Product save(Product newProduct) {
        newProduct.setNome(StringUtils.capitalize(newProduct.getNome()));
        return productRepository.save(newProduct);
    }

    public Page<Product> getAllProducts(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    public Optional<Product> getSingleProduct(String name) {
        return productRepository.findProductByNome(StringUtils.capitalize(name));
    }

    public Page<Optional<Product>> getProductsLike(String name, Pageable pageable) {
        return productRepository.findProductByNomeLike(name, pageable);
    }

    public String updateSingleProduct(
            String productName,
            String field,
            String newValue
    ) {
        Update updateDefinition = null;
        Query query = new Query().addCriteria(
                Criteria.where("nome").is(StringUtils.capitalize(productName))
        );

        if(field.equals("nome")) {
            updateDefinition = new Update().set(field, StringUtils.capitalize(newValue));
        }
        else if (field.equals("prezzo")){
            double newValueConverted = Double.parseDouble(newValue);
            updateDefinition = new Update().set(field, newValueConverted);
        }

        if(updateDefinition!=null){
            UpdateResult updateResult = mongoTemplate.updateFirst(
                    query,
                    updateDefinition,
                    Product.class
            );

            return updateResult.toString();
        }

        return null;
    }

    public List<Product> deleteProduct(String name) {
        return productRepository.deleteProductByNome(StringUtils.capitalize(name));
    }
}
