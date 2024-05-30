package com.trong.redisservice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.trong.redisservice.repository.ProductRepo;
import com.trong.redisservice.service.ProductService;
import model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@RestController
public class RedisController {
    @Autowired
    private ProductService productService;
    @Autowired
    private ObjectMapper redisObjectMapper;
    @Autowired
    private ProductRepo productRepo;

    @PostMapping("/redis")
    public String redis(@RequestBody Map<String, Object> keyword) throws JsonProcessingException {
        List<Product> productServices = productRepo.findAll();
        productService.saveAllProducts(productServices, "", "", null);
        return "success";
    }

    @GetMapping("/redis")
    public List<Product> redis() throws JsonProcessingException {
        return productService.getAllProducts("", "", null);
    }

    @GetMapping("/clear-all-product")
    public String clearAllProduct() throws JsonProcessingException {
        productService.clearAllProducts();
        return "success";
    }
}
