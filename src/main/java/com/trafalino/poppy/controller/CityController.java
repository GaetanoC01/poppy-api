package com.trafalino.poppy.controller;

import com.trafalino.poppy.dto.City;
import com.trafalino.poppy.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/cities")
@CrossOrigin(origins = "*")
public class CityController {

    @Autowired
    private CityService cityService;

    @PostMapping
    public ResponseEntity<City> createCity(@RequestBody City city) {
        return new ResponseEntity<City>(
                cityService.save(city),
                HttpStatus.CREATED
        );
    }

    @GetMapping("/{cityName}")
    public ResponseEntity<Optional<City>> getSingleCity(
            @PathVariable String cityName
    ){
        return new ResponseEntity<Optional<City>>(
                cityService.singleCity(cityName),
                HttpStatus.OK
        );
    }

    @DeleteMapping("/{cityName}")
    public ResponseEntity<List<City>> deleteCity(@PathVariable String cityName){
        return new ResponseEntity<List<City>>(
                cityService.deleteCity(cityName),
                HttpStatus.OK
        );
    }


}
