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
import java.util.Map;

@RestController
public class RedisController {
    @Autowired
    private ProductService productService;

    @PostMapping("/save-products")
    public ResponseEntity<String> saveProducts(@RequestBody Map<String, Object> param) {
        try {
            List<Product> products = (List<Product>) param.get("products");
            Map<String, Object> keywords = (Map<String, Object>) param.get("keywords");
            productService.saveAllProducts(products, keywords, null);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>("success", HttpStatus.OK);
    }

    @PostMapping("/get-products")
    public ResponseEntity<Object> getProducts(@RequestBody Map<String, Object> param) {
        try {
            return new ResponseEntity<>(productService.getAllProducts(param, null), HttpStatus.OK);
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
