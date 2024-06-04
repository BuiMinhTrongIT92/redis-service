package com.trong.redisservice.controller;

import com.trong.redisservice.model.Product;
import com.trong.redisservice.repository.ProductRepo;
import com.trong.redisservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RedisController {
    @Autowired
    private ProductService productService;
    @Autowired
    private ProductRepo productRepo;

    @PostMapping("/save-products")
    public ResponseEntity<String> saveProducts(@RequestBody Object param) {
        try {
            List<Product> products = (List<Product>) param;
            productService.saveAllProducts(products, "", "", null);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>("success", HttpStatus.OK);
    }

    @GetMapping("/get-products")
    public ResponseEntity<Object> getProducts() {
        try {
            return new ResponseEntity<>(productService.getAllProducts("", "", null), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/clear-product")
    public ResponseEntity<Object> clearAllProduct() {
        try {
            productService.clearAllProducts();
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>("success", HttpStatus.OK);
    }
}
