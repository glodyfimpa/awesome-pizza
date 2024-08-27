package com.glodyfimpa.awesome_pizza.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Order implements Serializable {

    private static final long serialVersionUID = -2698066248796658868L;

    private String orderId;
    private String client;
    private String address;
    private List<Pizza> pizze = new ArrayList<>();
    private OrderStatus status;

}

