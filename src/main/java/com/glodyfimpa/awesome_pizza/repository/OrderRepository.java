package com.glodyfimpa.awesome_pizza.repository;

import com.glodyfimpa.awesome_pizza.model.Order;
import com.glodyfimpa.awesome_pizza.model.OrderStatus;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class OrderRepository {

    private List<Order> orders = new ArrayList<>();

    public Order save(Order order) {
        orders.add(order);
        return order;
    }

    public List<Order> findAll() {
        return orders;
    }

    public Optional<Order> findById(String orderId) {
        return orders.stream().filter(o -> o.getOrderId().equals(orderId)).findFirst();
    }

    public void updateStatus(String orderId, String newStatus) {
        orders.stream()
                .filter(o -> o.getOrderId().equals(orderId))
                .forEach(o -> o.setStatus(OrderStatus.valueOf(newStatus)));
    }

}