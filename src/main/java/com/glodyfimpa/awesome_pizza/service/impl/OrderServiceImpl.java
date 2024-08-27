package com.glodyfimpa.awesome_pizza.service.impl;

import com.glodyfimpa.awesome_pizza.model.Order;
import com.glodyfimpa.awesome_pizza.model.OrderStatus;
import com.glodyfimpa.awesome_pizza.repository.OrderRepository;
import com.glodyfimpa.awesome_pizza.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    @Override
    public Order createOrder(Order order) {
        order.setOrderId(UUID.randomUUID().toString());
        order.setStatus(OrderStatus.IN_CODA);
        return orderRepository.save(order);
    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public Optional<Order> getOrderById(String orderId) {
        return orderRepository.findById(orderId);
    }

    @Override
    public void updateOrderStatus(String orderId, String newStatus) {
        orderRepository.updateStatus(orderId, newStatus);
    }

}