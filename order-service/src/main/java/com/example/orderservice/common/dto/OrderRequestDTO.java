package com.example.orderservice.common.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
@Builder
public class OrderRequestDTO {

    private long id;

    private String name;

    private String customerId;

    private String productType;

    private int quantity;

    private BigDecimal price;

    private Date orderDate;
}
