package com.glodyfimpa.awesome_pizza.dto;

import com.glodyfimpa.awesome_pizza.model.OrderStatus;
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
public class OrderDto implements Serializable {

    private static final long serialVersionUID = -1825735303263939994L;

    private String orderId;
    private String client;
    private String address;
    private List<PizzaDto> pizze = new ArrayList<>();
    private OrderStatus status;

}
