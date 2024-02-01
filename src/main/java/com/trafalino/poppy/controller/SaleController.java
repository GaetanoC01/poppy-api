package com.trafalino.poppy.controller;

import com.trafalino.poppy.dto.Sale;
import com.trafalino.poppy.dto.UpdateRecord;
import com.trafalino.poppy.service.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/sales")
@CrossOrigin(origins = "*")
public class SaleController {

    @Autowired
    SaleService saleService;

    @PostMapping
    public ResponseEntity<Sale> createSale(
            @RequestBody Sale newSale
    ) {
        return new ResponseEntity<Sale>(
                saleService.createSale(newSale)
                ,
                HttpStatus.CREATED
        );
    }

    @GetMapping
    public ResponseEntity<List<Sale>> getAllSales() {
        return new ResponseEntity<List<Sale>>(
                saleService.getAllSales()
                ,
                HttpStatus.OK
        );
    }

    @GetMapping("/year/{year}")
    public ResponseEntity<Optional<List<Sale>>> getSalesByYear(
            @PathVariable String year
    ) {
        int parsedYear = Integer.parseInt(year);
        return new ResponseEntity<Optional<List<Sale>>>(
                saleService.getSaleByYear(parsedYear)
                ,
                HttpStatus.OK
        );
    }

    @GetMapping("/month/{month}")
    public ResponseEntity<Optional<List<Sale>>> getSalesByMonth(
            @PathVariable String month
    ) {
        return new ResponseEntity<Optional<List<Sale>>>(
                saleService.getSaleByMonth(month)
                ,
                HttpStatus.OK
        );
    }

    @GetMapping("/wholesaler/{wholesalerName}")
    public ResponseEntity<Optional<List<Sale>>> getSalesByWholesaler(
            @PathVariable String wholesalerName
    ) {
        return new ResponseEntity<Optional<List<Sale>>>(
                saleService.getSaleByWholesaler(wholesalerName),
                HttpStatus.OK
        );
    }

    @GetMapping("/product/{productName}")
    public ResponseEntity<Optional<List<Sale>>> getSalesByProduct(
            @PathVariable String productName
    ) {
        return new ResponseEntity<Optional<List<Sale>>>(
                saleService.getSaleByProduct(productName)
                ,
                HttpStatus.OK
        );
    }

    @GetMapping("/city/{cityName}")
    public ResponseEntity<Optional<List<Sale>>> getSalesByCity(
            @PathVariable String cityName
    ) {
        return new ResponseEntity<Optional<List<Sale>>>(
                saleService.getSaleByCitta(cityName)
                ,
                HttpStatus.OK
        );
    }

    @GetMapping("/province/{provinceName}")
    public ResponseEntity<Optional<List<Sale>>> getSalesByProvince(
            @PathVariable String provinceName
    ) {
        return new ResponseEntity<Optional<List<Sale>>>(
                saleService.getSaleByProvince(provinceName)
                ,
                HttpStatus.OK
        );
    }

    @GetMapping("/region/{regionName}")
    public ResponseEntity<Optional<List<Sale>>> getSalesByRegion(
            @PathVariable String regionName
    ) {
        return new ResponseEntity<Optional<List<Sale>>>(
                saleService.getSaleByRegion(regionName)
                ,
                HttpStatus.OK
        );
    }

    @GetMapping("/cap/{capCode}")
    public ResponseEntity<Optional<List<Sale>>> getSalesByCap(
            @PathVariable String capCode
    ) {
        return new ResponseEntity<Optional<List<Sale>>>(
                saleService.getSaleByCap(capCode)
                ,
                HttpStatus.OK
        );
    }

    // returns null and OK if the record has not been updated
    @PutMapping("/{saleId}")
    public ResponseEntity<String> updateSale(
            @PathVariable String saleId,
            @RequestBody UpdateRecord requestBody
    ) {
        return new ResponseEntity<String>(
                saleService.updateSale(
                        saleId,
                        requestBody.fieldName(),
                        requestBody.value()
                )
                ,
                HttpStatus.OK
        );
    }

    @DeleteMapping("/{saleId}")
    public ResponseEntity<String> deleteSale(@PathVariable String saleId) {
        saleService.deleteSale(saleId);
        return new ResponseEntity<String>(
                "Record "+ saleId +" deleted.",
                HttpStatus.OK
        );
    }
}
