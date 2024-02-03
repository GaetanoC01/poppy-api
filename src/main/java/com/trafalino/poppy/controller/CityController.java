package com.trafalino.poppy.controller;

import com.trafalino.poppy.dto.City;
import com.trafalino.poppy.dto.UpdateRecord;
import com.trafalino.poppy.dto.UpdateRecordMulti;
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
    public ResponseEntity<City> createCity(@RequestBody City newCity) {
        return new ResponseEntity<City>(
                cityService.save(newCity),
                HttpStatus.CREATED
        );
    }

    @GetMapping
    public ResponseEntity<List<City>> getAllCities() {
        return new ResponseEntity<List<City>>(
                cityService.getAllCities(),
                HttpStatus.OK
        );
    }

    @GetMapping("/{cityName}")
    public ResponseEntity<Optional<City>> getSingleCity(
            @PathVariable String cityName
    ){
        return new ResponseEntity<Optional<City>>(
                cityService.getSingleCity(cityName),
                HttpStatus.OK
        );
    }

    @PutMapping("/{cityName}")
    public ResponseEntity<String> updateCity(
            @PathVariable String cityName,
            @RequestBody UpdateRecord requestBodySingle
            ) {
        return new ResponseEntity<String>(
                cityService.updateSingleCity(
                        cityName,
                        requestBodySingle.fieldName(),
                        requestBodySingle.value()
                ),
                HttpStatus.OK
        );
    }

    @PutMapping("/{cityName}/cap")
    public ResponseEntity<String> updateCityMulti(
            @PathVariable String cityName,
            @RequestBody UpdateRecordMulti requestBodyArray
            ) {

        if (requestBodyArray.operation().equals("add")){
            return new ResponseEntity<String>(
                    cityService.addToArrayCity(
                            cityName,
                            requestBodyArray.fieldName(),
                            requestBodyArray.value()
                    ),
                    HttpStatus.OK
            );
        } else if (requestBodyArray.operation().equals("remove")){
            return new ResponseEntity<String>(
                    cityService.removeFromArrayCity(
                            cityName,
                            requestBodyArray.fieldName(),
                            requestBodyArray.value()
                    ),
                    HttpStatus.OK
            );
        }

        return new ResponseEntity<String>(
                "Not Found",
                HttpStatus.NOT_FOUND
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
