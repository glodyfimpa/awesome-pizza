package com.glodyfimpa.awesome_pizza.service.impl;

import com.glodyfimpa.awesome_pizza.dto.OrderDto;
import com.glodyfimpa.awesome_pizza.dto.request.OrderRequest;
import com.glodyfimpa.awesome_pizza.mapper.OrderMapper;
import com.glodyfimpa.awesome_pizza.model.Order;
import com.glodyfimpa.awesome_pizza.model.OrderStatus;
import com.glodyfimpa.awesome_pizza.repository.OrderRepository;
import com.glodyfimpa.awesome_pizza.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.ListUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    @Override
    public OrderDto createOrder(OrderRequest orderRequest) {

        Order order = orderMapper.requestDtoToEntity(orderRequest);
        order.setOrderId(UUID.randomUUID().toString());
        order.setStatus(OrderStatus.IN_CODA);

        order = orderRepository.save(order);

        return orderMapper.entityToDto(order);
    }

    @Override
    public List<OrderDto> getAllOrders() {
        return orderMapper.entitiesToDtos(ListUtils.emptyIfNull(orderRepository.findAll()));
    }

    @Override
    public Optional<OrderDto> getOrderById(String orderId) {
        return orderRepository.findById(orderId).map(orderMapper::entityToDto);
    }

    @Override
    public void updateOrderStatus(String orderId, String newStatus) {
        orderRepository.updateStatus(orderId, newStatus);
    }

}