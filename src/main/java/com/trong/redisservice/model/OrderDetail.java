package com.trong.redisservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "orderdetails")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetail {
    @Id
    private Object _id;
    private Number orderNumber;
    private String productCode;
    private Number quantityOrdered;
    private Number priceEach;
    private Number orderLineNumber;
}
