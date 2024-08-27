package com.glodyfimpa.awesome_pizza.mapper;

import com.glodyfimpa.awesome_pizza.dto.OrderDto;
import com.glodyfimpa.awesome_pizza.dto.request.OrderRequest;
import com.glodyfimpa.awesome_pizza.model.Order;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring",
        uses = PizzaMapper.class,
        builder = @Builder(disableBuilder = true)
)
public interface OrderMapper {

    OrderDto entityToDto(Order order);

    Order dtoToEntity(OrderDto orderDto);

    Order requestDtoToEntity(OrderRequest orderRequest);

    List<OrderDto> entitiesToDtos(List<Order> order);
}
