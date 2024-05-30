package model;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Demo {
    public static void main(String[] args) {
        try {
            // Đọc file JSON
            ObjectMapper mapper = new ObjectMapper();
            List<Map<String, Object>> products = mapper.readValue(new File("/Users/trongbui/Documents/ProjectStudy/Microservice/redis-service/src/main/java/model/products.json"), new TypeReference<List<Map<String, Object>>>() {});

            // Nhân số lượng sản phẩm lên 600 lần
            List<Map<String, Object>> newProducts = new ArrayList<>();
            for (Map<String, Object> product : products) {
                for (int i = 0; i < 600; i++) {
                    Map<String, Object> newProduct = new java.util.HashMap<>(product);
                    newProduct.put("_id", product.get("_id") + "_" + i);
                    newProducts.add(newProduct);
                }
            }

            // Ghi ra file JSON mới
            mapper.writeValue(new File("/Users/trongbui/Documents/ProjectStudy/Microservice/redis-service/src/main/java/model/products.json"), newProducts);

            System.out.println("Hoàn thành! File mới đã được ghi là new_products.json");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
