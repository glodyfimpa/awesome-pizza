package com.glodyfimpa.awesome_pizza.service;

import com.glodyfimpa.awesome_pizza.dto.OrderDto;
import com.glodyfimpa.awesome_pizza.dto.request.OrderRequest;
import com.glodyfimpa.awesome_pizza.mapper.OrderMapper;
import com.glodyfimpa.awesome_pizza.model.Order;
import com.glodyfimpa.awesome_pizza.model.OrderStatus;
import com.glodyfimpa.awesome_pizza.repository.OrderRepository;
import com.glodyfimpa.awesome_pizza.service.impl.OrderServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

    private static final String CLIENT_NAME = "Glody Fimpa";

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private OrderMapper orderMapper;

    @InjectMocks
    private OrderServiceImpl orderService;

    @Test
    void createOrderTest() {

        String orderId = UUID.randomUUID().toString();

        OrderRequest orderRequest = new OrderRequest();
        orderRequest.setClient(CLIENT_NAME);

        Order order = new Order();
        order.setClient(CLIENT_NAME);

        OrderDto orderDto = new OrderDto();
        orderDto.setClient(CLIENT_NAME);

        when(orderMapper.requestDtoToEntity(any(OrderRequest.class))).thenReturn(order);

        when(orderRepository.save(any(Order.class))).thenAnswer(invocation -> {
            Order savedOrder = invocation.getArgument(0);
            savedOrder.setOrderId(orderId);
            return savedOrder;
        });

        orderDto.setOrderId(orderId);
        orderDto.setStatus(OrderStatus.IN_CODA);
        when(orderMapper.entityToDto(any(Order.class))).thenReturn(orderDto);

        OrderDto createdOrder = orderService.createOrder(orderRequest);

        assertThat(createdOrder.getStatus()).isEqualTo(OrderStatus.IN_CODA);
        assertTrue(createdOrder.getOrderId() != null && !createdOrder.getOrderId().isEmpty());
        verify(orderRepository, times(1)).save(any(Order.class));
    }

    @Test
    void getAllOrdersTest() {

        List<Order> orders = getOrders();
        List<OrderDto> ordersDto = getOrdersDto();

        when(orderRepository.findAll()).thenReturn(orders);

        when(orderMapper.entitiesToDtos(anyList())).thenReturn(ordersDto);

        List<OrderDto> getAllOrders = orderService.getAllOrders();

        assertThat(getAllOrders).isNotEmpty().isEqualTo(ordersDto);
    }

    @Test
    void getOrderByIdTest() {

        String orderId = "1";

        Order order =
                Order.builder()
                        .orderId(orderId)
                        .status(OrderStatus.IN_PREPARAZIONE)
                        .build();

        OrderDto orderDto =
                OrderDto.builder()
                        .orderId(orderId)
                        .status(OrderStatus.IN_PREPARAZIONE)
                        .build();

        when(orderRepository.findById(anyString())).thenReturn(Optional.of(order));

        when(orderMapper.entityToDto(any(Order.class))).thenReturn(orderDto);

        Optional<OrderDto> orderOptional = orderService.getOrderById(orderId);

        assertThat(orderOptional).isPresent().contains(orderDto);
    }

    @Test
    void updateOrderStatusTest() {

        String orderId = UUID.randomUUID().toString();

        orderService.updateOrderStatus(orderId, OrderStatus.IN_PREPARAZIONE.name());

        verify(orderRepository, times(1)).updateStatus(orderId, OrderStatus.IN_PREPARAZIONE.name());
    }

    private List<Order> getOrders() {

        Order orderInCoda =
                Order.builder()
                        .orderId(UUID.randomUUID().toString())
                        .status(OrderStatus.IN_CODA)
                        .build();

        Order orderInPreparazione =
                Order.builder()
                        .orderId(UUID.randomUUID().toString())
                        .status(OrderStatus.IN_PREPARAZIONE)
                        .build();

        Order orderPronto =
                Order.builder()
                        .orderId(UUID.randomUUID().toString())
                        .status(OrderStatus.PRONTO)
                        .build();

        Order orderConsegnato =
                Order.builder()
                        .orderId(UUID.randomUUID().toString())
                        .status(OrderStatus.CONSEGNATO)
                        .build();

        return List.of(orderInCoda, orderInPreparazione, orderPronto, orderConsegnato);
    }

    private List<OrderDto> getOrdersDto() {

        OrderDto orderInCoda =
                OrderDto.builder()
                        .orderId(UUID.randomUUID().toString())
                        .status(OrderStatus.IN_CODA)
                        .build();

        OrderDto orderInPreparazione =
                OrderDto.builder()
                        .orderId(UUID.randomUUID().toString())
                        .status(OrderStatus.IN_PREPARAZIONE)
                        .build();

        OrderDto orderPronto =
                OrderDto.builder()
                        .orderId(UUID.randomUUID().toString())
                        .status(OrderStatus.PRONTO)
                        .build();

        OrderDto orderConsegnato =
                OrderDto.builder()
                        .orderId(UUID.randomUUID().toString())
                        .status(OrderStatus.CONSEGNATO)
                        .build();

        return List.of(orderInCoda, orderInPreparazione, orderPronto, orderConsegnato);
    }
}


