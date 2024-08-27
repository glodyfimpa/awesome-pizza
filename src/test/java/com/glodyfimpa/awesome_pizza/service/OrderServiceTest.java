package com.glodyfimpa.awesome_pizza.service;

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

    @Mock
    private OrderRepository orderRepository;

    @InjectMocks
    private OrderServiceImpl orderService;

    @Test
    void createOrderTest() {

        Order order = new Order();
        order.setClient("Glody Fimpa");

        when(orderRepository.save(any(Order.class))).thenAnswer(invocation -> {
            Order savedOrder = invocation.getArgument(0);
            savedOrder.setOrderId(UUID.randomUUID().toString());
            return savedOrder;
        });

        Order createdOrder = orderService.createOrder(order);

        assertThat(createdOrder.getStatus()).isEqualTo(OrderStatus.IN_CODA);
        assertTrue(createdOrder.getOrderId() != null && !createdOrder.getOrderId().isEmpty());
        verify(orderRepository, times(1)).save(any(Order.class));
    }

    @Test
    void getAllOrdersTest() {

        List<Order> ordersExpected = getOrders();

        when(orderRepository.findAll()).thenReturn(ordersExpected);

        List<Order> orders = orderService.getAllOrders();

        assertThat(orders).isNotEmpty().isEqualTo(ordersExpected);
    }

    @Test
    void getOrderByIdTest() {

        Order order =
                Order.builder()
                        .orderId("1")
                        .status(OrderStatus.IN_PREPARAZIONE)
                        .build();

        when(orderRepository.findById(anyString())).thenReturn(Optional.of(order));

        Optional<Order> orderOptional = orderService.getOrderById("1");

        assertThat(orderOptional).isPresent().contains(order);
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
}


