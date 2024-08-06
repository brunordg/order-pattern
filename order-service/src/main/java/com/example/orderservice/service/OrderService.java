package com.example.orderservice.service;

import com.example.orderservice.common.dto.OrderRequestDTO;
import com.example.orderservice.common.mapper.OrderDTOtoEntityMapper;
import com.example.orderservice.common.mapper.OrderEntityToOutboxEntityMapper;
import com.example.orderservice.entity.Order;
import com.example.orderservice.repository.OrderRepository;
import com.example.orderservice.repository.OutboxRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    private final OutboxRepository outboxRepository;

    private final OrderDTOtoEntityMapper orderDTOtoEntityMapper;

    private final OrderEntityToOutboxEntityMapper outboxEntityMapper;

    @Transactional
    public Order createOrder(OrderRequestDTO orderRequestDTO) {
        Order orderSaved = orderRepository.save(orderDTOtoEntityMapper.map(orderRequestDTO));

        outboxRepository.save(outboxEntityMapper.map(orderSaved));

        return orderSaved;
    }
}
