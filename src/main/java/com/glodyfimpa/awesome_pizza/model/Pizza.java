package com.glodyfimpa.awesome_pizza.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Pizza implements Serializable {

    private static final long serialVersionUID = 4088481569943005111L;

    private String name;
    private int quantity;
}