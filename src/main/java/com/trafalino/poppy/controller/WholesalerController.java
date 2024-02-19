package com.trafalino.poppy.controller;

import com.trafalino.poppy.dto.UpdateRecord;
import com.trafalino.poppy.dto.Wholesaler;
import com.trafalino.poppy.service.WholesalerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/wholesalers")
@CrossOrigin(origins = "*")
public class WholesalerController {

    @Autowired
    private WholesalerService wholesalerService;

    @PostMapping
    public ResponseEntity<Wholesaler> createWholesaler(
            @RequestBody Wholesaler requestBody
    ) {
        return new ResponseEntity<Wholesaler>(
                wholesalerService.save(requestBody)
                ,
                HttpStatus.OK
        );
    }

    @GetMapping
    public ResponseEntity<List<Wholesaler>> getAllWholesalers() {
        return new ResponseEntity<List<Wholesaler>>(
                wholesalerService.getAllWholesalers()
                ,
                HttpStatus.OK
        );
    }

    @GetMapping("/{wholesalerName}")
    public ResponseEntity<Optional<Wholesaler>> getSingleWholesaler(
            @PathVariable String wholesalerName
    ) {
        return new ResponseEntity<Optional<Wholesaler>>(
                wholesalerService.getSingleWholesaler(wholesalerName)
                ,
                HttpStatus.OK
        );
    }

    @GetMapping("/search/{wholesalerName}")
    public ResponseEntity<List<Optional<Wholesaler>>> getWholesalersSearch(
            @PathVariable String wholesalerName
    ) {
        return new ResponseEntity<List<Optional<Wholesaler>>>(
                wholesalerService.getWholesalersLike(wholesalerName)
                ,
                HttpStatus.OK
        );
    }

    @PutMapping("/{wholesalerName}")
    public ResponseEntity<String> updateWholesaler(
            @PathVariable String wholesalerName,
            @RequestBody UpdateRecord requestBody
            ) {
        return new ResponseEntity<String>(
                wholesalerService.updateSingleWholesaler(
                        wholesalerName,
                        requestBody.fieldName(),
                        requestBody.value()
                )
                ,
                HttpStatus.OK
        );
    }

    @DeleteMapping("/{wholesalerName}")
    public ResponseEntity<List<Wholesaler>> deleteWholesaler(
            @PathVariable String wholesalerName
    ) {
        return new ResponseEntity<List<Wholesaler>>(
                wholesalerService.deleteWholesaler(wholesalerName)
                ,
                HttpStatus.OK
        );
    }
}
