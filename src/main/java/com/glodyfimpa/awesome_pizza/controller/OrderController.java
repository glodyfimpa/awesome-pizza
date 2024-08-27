package com.glodyfimpa.awesome_pizza.controller;

import com.glodyfimpa.awesome_pizza.dto.OrderDto;
import com.glodyfimpa.awesome_pizza.dto.request.OrderRequest;
import com.glodyfimpa.awesome_pizza.model.Order;
import com.glodyfimpa.awesome_pizza.model.OrderStatus;
import com.glodyfimpa.awesome_pizza.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(summary = "Create a new order")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Order created",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = OrderDto.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid input",
                    content = @Content)
    })
    @PostMapping
    public ResponseEntity<OrderDto> createOrder(@RequestBody OrderRequest orderRequest) {
        OrderDto newOrder = orderService.createOrder(orderRequest);
        return new ResponseEntity<>(newOrder, HttpStatus.CREATED);
    }

    @Operation(summary = "Get all orders")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found orders",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = OrderDto.class))})
    })
    @GetMapping
    public ResponseEntity<List<OrderDto>> getAllOrders() {
        return new ResponseEntity<>(orderService.getAllOrders(), HttpStatus.OK);
    }

    @Operation(summary = "Get an order by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the order",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Order.class))}),
            @ApiResponse(responseCode = "404", description = "Order not found",
                    content = @Content)
    })
    @GetMapping("/{orderId}")
    public ResponseEntity<OrderDto> getOrderById(@PathVariable String orderId) {
        return orderService.getOrderById(orderId)
                .map(order -> new ResponseEntity<>(order, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Operation(summary = "Update order status")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Order status updated",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = OrderDto.class))}),
            @ApiResponse(responseCode = "404", description = "Order not found",
                    content = @Content)
    })
    @PutMapping("/{orderId}")
    public ResponseEntity<Void> updateOrderStatus(@PathVariable String orderId, @RequestParam OrderStatus newStatus) {
        orderService.updateOrderStatus(orderId, newStatus.name());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
