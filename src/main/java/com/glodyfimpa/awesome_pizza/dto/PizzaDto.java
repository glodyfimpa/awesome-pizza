package com.glodyfimpa.awesome_pizza.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PizzaDto implements Serializable {

    private static final long serialVersionUID = -1079096536213015519L;

    private String name;
    private int quantity;
}
