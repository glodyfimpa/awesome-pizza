package com.glodyfimpa.awesome_pizza.repository;

import com.glodyfimpa.awesome_pizza.model.Order;

import java.util.List;
import java.util.Optional;

public interface OrderRepository {

    Order save(Order order);

    List<Order> findAll();

    Optional<Order> findById(String orderId);

    void updateStatus(String orderId, String newStatus);

}