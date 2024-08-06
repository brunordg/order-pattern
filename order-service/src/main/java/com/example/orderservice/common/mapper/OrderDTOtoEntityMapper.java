package com.example.orderservice.common.mapper;

import com.example.orderservice.common.dto.OrderRequestDTO;
import com.example.orderservice.entity.Order;
import org.springframework.stereotype.Component;

@Component
public class OrderDTOtoEntityMapper {

    public Order map(OrderRequestDTO orderRequestDTO) {
        return Order.builder()
                .customerId(orderRequestDTO.getCustomerId())
                .name(orderRequestDTO.getName())
                .id(orderRequestDTO.getId())
                .orderDate(orderRequestDTO.getOrderDate())
                .price(orderRequestDTO.getPrice())
                .productType(orderRequestDTO.getProductType())
                .quantity(orderRequestDTO.getQuantity())
                .build();

    }
}
