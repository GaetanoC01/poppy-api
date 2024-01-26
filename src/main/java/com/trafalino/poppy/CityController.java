package com.trafalino.poppy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/cities")
@CrossOrigin(origins = "*")
public class CityController {

    @Autowired
    private CityService cityService;

    @GetMapping("/{cityName}")
    public ResponseEntity<Optional<City>> getSingleCity(
            @PathVariable String cityName
    ){
        return new ResponseEntity<Optional<City>>(
                cityService.singleCity(cityName),
                HttpStatus.OK
        );
    }
}
