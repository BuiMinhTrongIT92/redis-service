package com.trong.redisservice.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.trong.redisservice.model.Product;
import com.trong.redisservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;
    @Autowired
    private ObjectMapper redisObjectMapper;

    private String getKey(String actionType, String keyword, String productId, PageRequest pageRequest) {
        String key = "";
        if (null != pageRequest) {
            int pageNumber = pageRequest.getPageNumber();
            int pageSize = pageRequest.getPageSize();
            Sort sort = pageRequest.getSort();
            String sortDirection = sort.getOrderFor("id").getDirection() == Sort.Direction.ASC ? "asc" : "desc";
            key = String.format("%s_%s_%s_%s_%s_%s", actionType, keyword, productId, pageNumber, pageSize, sortDirection);
        } else {
            key = String.format("%s_%s", actionType, keyword, productId);
        }
        return key;
    }

    @Override
    public List<Product> getAllProducts(String keyword, String id, PageRequest pageRequest) {
        List<Product> products = null;
        try {
            String key = getKey("all-products".toLowerCase(), keyword, id, pageRequest);
            String json = (String) redisTemplate.opsForValue().get(key);
            products = redisObjectMapper.readValue(json, new TypeReference<List<Product>>() {
            });
        } catch (JsonProcessingException e) {
            return null;
        }
        return products;
    }

    @Override
    public void saveAllProducts(List<Product> products, String keyword, String id, PageRequest pageRequest) {
        try {
            String key = getKey("all-products".toLowerCase(), keyword, id, pageRequest);
            String json = redisObjectMapper.writeValueAsString(products);
            redisTemplate.opsForValue().set(key, json);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void clearAllProducts() {
        redisTemplate.getConnectionFactory().getConnection().serverCommands().flushAll();
    }
}
