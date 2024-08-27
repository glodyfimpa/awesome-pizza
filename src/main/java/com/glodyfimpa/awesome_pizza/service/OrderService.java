package com.glodyfimpa.awesome_pizza.service;

import com.glodyfimpa.awesome_pizza.model.Order;

import java.util.List;
import java.util.Optional;

public interface OrderService {

    Order createOrder(Order order);

    List<Order> getAllOrders();

    Optional<Order> getOrderById(String orderId);

    void updateOrderStatus(String orderId, String newStatus);

}