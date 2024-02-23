package com.trafalino.poppy.controller;

import com.trafalino.poppy.dto.Sale;
import com.trafalino.poppy.dto.UpdateRecord;
import com.trafalino.poppy.service.SaleService;
import com.trafalino.poppy.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<Page<Sale>> getAllSales(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int sizePerPage
    ) {
        Pageable pageable = PageRequest.of(
                page,
                sizePerPage,
                Sort.by("anno").descending().and(
                        Sort.by("meseEncoded").descending()
                ).and(
                        Sort.by("grossista").ascending()
                ).and(
                        Sort.by("prodotto").ascending()
                )
        );
        return new ResponseEntity<Page<Sale>>(
                saleService.getAllSales(pageable)
                ,
                HttpStatus.OK
        );
    }


    @GetMapping("filter")
    public ResponseEntity<List<Sale>> filterSale(
            @RequestParam(value = "mese", required = false) String month,
            @RequestParam(value = "anno", required = false) Integer year,
            @RequestParam(value = "grossista", required = false) String wholesaler,
            @RequestParam(value = "citta", required = false) String city,
            @RequestParam(value = "provincia", required = false) String province,
            @RequestParam(value = "regione", required = false) String region,
            @RequestParam(value = "cap", required = false) String cap,
            @RequestParam(value = "prodotto", required = false) String product
    ) {
        return new ResponseEntity<List<Sale>>(
                saleService.filterData(
                        StringUtils.capitalize(month),
                        year,
                        StringUtils.capitalize(wholesaler),
                        StringUtils.capitalize(city),
                        StringUtils.capitalize(province),
                        StringUtils.capitalize(region),
                        StringUtils.capitalize(cap),
                        StringUtils.capitalize(product)
                )
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
