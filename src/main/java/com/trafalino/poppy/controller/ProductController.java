package com.trafalino.poppy.controller;

import com.trafalino.poppy.dto.Product;
import com.trafalino.poppy.dto.UpdateRecord;
import com.trafalino.poppy.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/products")
@CrossOrigin(origins = "*")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping
    public ResponseEntity<Product> createProduct(
            @RequestBody Product requestBody
    ){
        return new ResponseEntity<Product>(
                productService.save(requestBody)
                ,
                HttpStatus.CREATED
        );
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        return new ResponseEntity<List<Product>>(
                productService.getAllProducts()
                ,
                HttpStatus.OK
        );
    }

    @GetMapping("/{productName}")
    public ResponseEntity<Optional<Product>> getSingleProduct(
            @PathVariable String productName
    ) {
        return new ResponseEntity<Optional<Product>>(
                productService.getSingleProduct(productName)
                ,
                HttpStatus.OK
        );
    }

    @GetMapping("/search/{productName}")
    public ResponseEntity<List<Optional<Product>>> getProductsSearch(
            @PathVariable String productName
    ) {
        return new ResponseEntity<List<Optional<Product>>>(
                productService.getProductsLike(productName)
                ,
                HttpStatus.OK
        );
    }

    @PutMapping("/{productName}")
    public ResponseEntity<String> updateProduct(
            @PathVariable String productName,
            @RequestBody UpdateRecord requestBody
            ) {

        return new ResponseEntity<String>(
                productService.updateSingleProduct(
                        productName,
                        requestBody.fieldName(),
                        requestBody.value()
                )
                ,
                HttpStatus.OK
        );
    }

    @DeleteMapping("/{productName}")
    public ResponseEntity<List<Product>> deleteProduct(
            @PathVariable String productName
    ) {
        return new ResponseEntity<List<Product>>(
                productService.deleteProduct(productName)
                ,
                HttpStatus.OK
        );
    }
}
