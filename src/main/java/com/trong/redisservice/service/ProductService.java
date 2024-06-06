package com.trong.redisservice.service;

import com.trong.redisservice.model.Product;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Map;


public interface ProductService {
    List<Product> getAllProducts(Map<String, Object> keyword, PageRequest pageRequest);

    void saveAllProducts(List<Product> products, Map<String, Object> keyword, PageRequest pageRequest);

    void clearAllProducts();
}
