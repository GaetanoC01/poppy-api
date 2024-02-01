package com.trafalino.poppy.service;

import com.mongodb.client.result.UpdateResult;
import com.trafalino.poppy.dto.City;
import com.trafalino.poppy.dto.Product;
import com.trafalino.poppy.dto.Sale;
import com.trafalino.poppy.dto.Wholesaler;
import com.trafalino.poppy.repository.CityRepository;
import com.trafalino.poppy.repository.ProductRepository;
import com.trafalino.poppy.repository.SaleRepository;
import com.trafalino.poppy.repository.WholesalerRepository;
import com.trafalino.poppy.util.ComputationUtils;
import com.trafalino.poppy.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

//TODO: Add logic to uptade totaleVendite if qty price or discount are changed
@Service
public class SaleService {
    private String[] numericFields = {"anno", "quantita"};
    private String[] doubleFields = {"totaleVendite", "scontistica", "prezzo"};
    private List numericFieldsList = Arrays.asList(numericFields);
    private List doubleFieldsList = Arrays.asList(doubleFields);

    @Autowired
    private SaleRepository saleRepository;

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private WholesalerRepository wholesalerRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    // Create Service
    // This method takes values from other documents in the database in order
    // to enrich the new entry
    public Sale createSale(Sale newSale) {
        double totalSale;

        String cityName = newSale.getCitta();
        String wholesalerName = newSale.getGrossista();
        String productName = newSale.getProdotto();

        Optional<City> city = cityRepository.findCityByNome(cityName);
        Optional<Wholesaler> wholesaler = wholesalerRepository
                .findWholesalerByNome(wholesalerName);
        Optional<Product> product = productRepository
                .findProductByNome(productName);

        newSale.setPrezzo(product.get().getPrezzo());
        newSale.setScontistica(wholesaler.get().getScontistica());
        newSale.setCap(city.get().getCap());
        newSale.setProvincia(city.get().getProvince());
        newSale.setRegione(city.get().getRegion());

        totalSale = ComputationUtils.computeSale(
                newSale.getPrezzo(),
                newSale.getScontistica(),
                newSale.getQuantita()
        );

        newSale.setTotaleVendite(totalSale);

        return saleRepository.save(newSale);
    }

    // Get Service
    public List<Sale> getAllSales() {
        return saleRepository.findAll();
    }
    public Optional<List<Sale>> getSaleByYear(int year) {
        return saleRepository.findSaleByAnno(year);
    }
    public Optional<List<Sale>> getSaleByMonth(String month) {
        return saleRepository.findSaleByMese(StringUtils.capitalize(month));
    }
    public Optional<List<Sale>> getSaleByWholesaler(String wholesaler) {
        return saleRepository.findSaleByGrossista(StringUtils.capitalize(wholesaler));
    }
    public Optional<List<Sale>> getSaleByProduct(String product) {
        return saleRepository.findSaleByProdotto(StringUtils.capitalize(product));
    }
    public Optional<List<Sale>> getSaleByCitta(String city) {
        return saleRepository.findSaleByCitta(StringUtils.capitalize(city));
    }
    public Optional<List<Sale>> getSaleByCap(String cap) {
        return saleRepository.findSaleByCap(cap);
    }
    public Optional<List<Sale>> getSaleByProvince(String province) {
        return saleRepository.findSaleByProvincia(StringUtils.capitalize(province));
    }
    public Optional<List<Sale>> getSaleByRegion(String region) {
        return saleRepository.findSaleByRegione(StringUtils.capitalize(region));
    }

    // Update Service
    public String updateSale(
            String saleId,
            String field,
            String value
    ) {
        Update updateDefinition = null;
        Query query = new Query().addCriteria(
                Criteria.where("_id").is(saleId)
        );

        if (doubleFieldsList.contains(field)) {
            double valueConverted = Double.parseDouble(value);
            updateDefinition = new Update().set(field , valueConverted);
        } else if (numericFieldsList.contains(field)) {
            int valueConverted = Integer.parseInt(value);
            updateDefinition = new Update().set(field, valueConverted);
        } else {
            updateDefinition = new Update().set(field, StringUtils.capitalize(value));
        }

        if(updateDefinition!=null) {
            UpdateResult updateResult = mongoTemplate.updateFirst(
                    query,
                    updateDefinition,
                    Sale.class
            );

            return updateResult.toString();
        }

        return null;
    }

    // Delete Service
    public void deleteSale(String id) {
        saleRepository.deleteById(id);
    }

}