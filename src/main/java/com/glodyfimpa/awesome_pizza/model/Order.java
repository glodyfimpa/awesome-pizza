package com.glodyfimpa.awesome_pizza.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Order {

    private String orderId;
    private String client;
    private String address;
    private List<Pizza> pizze = new ArrayList<>();
    private OrderStatus status;

}

