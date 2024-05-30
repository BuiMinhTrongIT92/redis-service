package com.trong.redisservice.service;

import com.trong.redisservice.model.Product;
import org.springframework.data.domain.PageRequest;

import java.util.List;


public interface ProductService {
    List<Product> getAllProducts(String keyword, String id, PageRequest pageRequest);

    void saveAllProducts(List<Product> products, String keyword, String id, PageRequest pageRequest);

    void clearAllProducts();
}
