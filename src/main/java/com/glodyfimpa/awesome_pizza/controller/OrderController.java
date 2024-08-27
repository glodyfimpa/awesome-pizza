package com.glodyfimpa.awesome_pizza.controller;

import com.glodyfimpa.awesome_pizza.model.Order;
import com.glodyfimpa.awesome_pizza.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody Order order) {
        Order newOrder = orderService.createOrder(order);
        return new ResponseEntity<>(newOrder, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Order>> getAllOrders() {
        return new ResponseEntity<>(orderService.getAllOrders(), HttpStatus.OK);
    }

    @GetMapping("/{orderId}/status")
    public ResponseEntity<Order> getOrderStatus(@PathVariable String orderId) {
        return orderService.getOrderById(orderId)
                .map(order -> new ResponseEntity<>(order, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{orderId}/status")
    public ResponseEntity<Void> updateOrderStatus(@PathVariable String orderId, @RequestBody String newStatus) {
        orderService.updateOrderStatus(orderId, newStatus);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
