package com.glodyfimpa.awesome_pizza.service;

import com.glodyfimpa.awesome_pizza.dto.OrderDto;
import com.glodyfimpa.awesome_pizza.dto.request.OrderRequest;

import java.util.List;
import java.util.Optional;

public interface OrderService {

    OrderDto createOrder(OrderRequest orderRequest);

    List<OrderDto> getAllOrders();

    Optional<OrderDto> getOrderById(String orderId);

    void updateOrderStatus(String orderId, String newStatus);

}