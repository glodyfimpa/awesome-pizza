package com.glodyfimpa.awesome_pizza.mapper;

import com.glodyfimpa.awesome_pizza.dto.PizzaDto;
import com.glodyfimpa.awesome_pizza.model.Pizza;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring",
        builder = @Builder(disableBuilder = true)
)
public interface PizzaMapper {

    PizzaDto entityToDto(Pizza order);

    Pizza dtoToEntity(PizzaDto orderDto);
}
