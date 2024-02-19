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
import com.trafalino.poppy.util.MonthEncoder;
import com.trafalino.poppy.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

//TODO: Add logic to uptade totaleVendite if qty price or discount are changed
//TODO: add variables numeric fields and double fields to an xml file and read from it

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

        newSale.setCitta(StringUtils.capitalize(newSale.getCitta()));
        newSale.setGrossista(StringUtils.capitalize(newSale.getGrossista()));
        newSale.setProdotto(StringUtils.capitalize(newSale.getProdotto()));
        newSale.setMese(StringUtils.capitalize(newSale.getMese()));

        Integer monthEncoded = MonthEncoder.encode(newSale.getMese());
        newSale.setMeseEncoded(monthEncoded);

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
        newSale.setCap(city.get().getCap()[0]);


        newSale.setProvincia(city.get().getProvincia().getNome());
        newSale.setRegione(city.get().getRegione().getNome());

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
        return saleRepository.findAll(Sort.by(
                Sort.Order.desc("anno"),
                Sort.Order.desc("meseEncoded")
                )
        );
    }
    public List<Sale> filterData(
            String month,
            Integer year,
            String wholesaler,
            String city,
            String province,
            String region,
            String cap,
            String product
    ) {
        Query query = new Query();

        if(month != null) {
            query.addCriteria(Criteria.where("mese").is(month));
        }
        if(year != null) {
            query.addCriteria(Criteria.where("anno").is(year));
        }
        if(wholesaler != null) {
            query.addCriteria(Criteria.where("grossista").is(wholesaler));
        }
        if(city != null) {
            query.addCriteria(Criteria.where("citta").is(city));
        }
        if(province != null) {
            query.addCriteria(Criteria.where("provincia").is(province));
        }
        if(region != null) {
            query.addCriteria(Criteria.where("regione").is(region));
        }
        if(cap != null) {
            query.addCriteria(Criteria.where("cap").is(cap));
        }
        if(product != null) {
            query.addCriteria(Criteria.where("prodotto").is(product));
        }

        return mongoTemplate.find(query, Sale.class);
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
