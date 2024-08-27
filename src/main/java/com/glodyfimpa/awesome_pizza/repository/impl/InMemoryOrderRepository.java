package com.glodyfimpa.awesome_pizza.repository.impl;

import com.glodyfimpa.awesome_pizza.model.Order;
import com.glodyfimpa.awesome_pizza.model.OrderStatus;
import com.glodyfimpa.awesome_pizza.repository.OrderRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class InMemoryOrderRepository implements OrderRepository {

    private List<Order> orders = new ArrayList<>();

    @Override
    public Order save(Order order) {
        orders.add(order);
        return order;
    }

    @Override
    public List<Order> findAll() {
        return orders;
    }

    @Override
    public Optional<Order> findById(String orderId) {
        return orders.stream().filter(o -> o.getOrderId().equals(orderId)).findFirst();
    }

    @Override
    public void updateStatus(String orderId, String newStatus) {
        orders.stream()
                .filter(o -> o.getOrderId().equals(orderId))
                .forEach(o -> o.setStatus(OrderStatus.valueOf(newStatus)));
    }

}