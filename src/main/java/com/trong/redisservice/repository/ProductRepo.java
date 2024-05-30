package com.trong.redisservice.repository;

import com.trong.redisservice.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepo extends MongoRepository<Product, String> {
}
