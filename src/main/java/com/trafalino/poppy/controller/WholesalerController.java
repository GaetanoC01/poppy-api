package com.trafalino.poppy.controller;

import com.trafalino.poppy.dto.UpdateRecord;
import com.trafalino.poppy.dto.Wholesaler;
import com.trafalino.poppy.service.WholesalerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    public ResponseEntity<Page<Wholesaler>> getAllWholesalers(
            @RequestParam(defaultValue = "0") int page
    ) {
        Pageable pageable = PageRequest.of(page, 10, Sort.Direction.ASC, "nome");
        return new ResponseEntity<Page<Wholesaler>>(
                wholesalerService.getAllWholesalers(pageable)
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
    public ResponseEntity<Page<Optional<Wholesaler>>> getWholesalersSearch(
            @PathVariable String wholesalerName,
            @RequestParam(defaultValue = "0") int page
    ) {
        Pageable pageable = PageRequest.of(page, 10, Sort.Direction.ASC, "nome");
        return new ResponseEntity<Page<Optional<Wholesaler>>>(
                wholesalerService.getWholesalersLike(wholesalerName, pageable)
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
