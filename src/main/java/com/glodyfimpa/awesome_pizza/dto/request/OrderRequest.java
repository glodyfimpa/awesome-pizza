package com.glodyfimpa.awesome_pizza.dto.request;

import com.glodyfimpa.awesome_pizza.dto.PizzaDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class OrderRequest implements Serializable {

    @Serial
    private static final long serialVersionUID = -1825735303263939994L;

    private String client;
    private String address;
    private List<PizzaDto> pizze = new ArrayList<>();
}
