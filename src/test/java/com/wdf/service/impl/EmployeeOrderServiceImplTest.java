package com.wdf.service.impl;

import com.wdf.entity.Order;
import com.wdf.repository.OrderCache;
import com.wdf.repository.OrderQueue;
import com.wdf.tool.TestDataCreator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EmployeeOrderServiceImplTest {

    @InjectMocks
    EmployeeOrderServiceImpl service;
    @Mock
    OrderCache orderCache;
    @Mock
    OrderQueue orderQueue;

    @Test
    void getAllOrders() {
        Order order1 = TestDataCreator.createOrderWithOneItem(1, 1, 5);
        Order order2 = TestDataCreator.createOrderWithOneItem(2, 1, 5);
        Order order3 = TestDataCreator.createOrderWithOneItem(3, 1, 5);

        List<Order> orders = new ArrayList<>();
        orders.add(order1);
        orders.add(order2);
        orders.add(order3);

        when(orderCache.getAllOrders()).thenReturn(orders);

        Assertions.assertEquals(orders, service.getAllOrders());

        when(orderCache.getAllOrders()).thenReturn(Collections.emptyList());
        Assertions.assertEquals(0, service.getAllOrders().size());

    }

    @Test
    void getNextDelivery() {

        Order order1 = TestDataCreator.createOrderWithOneItem(1, 1, 15);
        Order order2 = TestDataCreator.createOrderWithOneItem(2, 1, 15);
        Order order3 = TestDataCreator.createOrderWithOneItem(3, 1, 15);
        Order order4 = TestDataCreator.createOrderWithOneItem(4, 1, 15);
        List<Order> orders = new ArrayList<>();
        orders.add(order1);
        orders.add(order2);
        orders.add(order3);
        orders.add(order4);

        List<Order> cart = new ArrayList<>();

        when(orderQueue.peek()).thenReturn(Optional.ofNullable(orders.get(0)),
                Optional.ofNullable(orders.get(1)),
                Optional.ofNullable(orders.get(2)),
                Optional.ofNullable(orders.get(3)));
        when(orderQueue.poll()).thenReturn(Optional.ofNullable(orders.get(0)),
                Optional.ofNullable(orders.get(1)),
                Optional.ofNullable(orders.get(2)),
                Optional.ofNullable(orders.get(3)));

        List<Order> nextDelivery = service.getNextDelivery();

        Assertions.assertEquals(3,nextDelivery.size());
        Assertions.assertEquals(order1,nextDelivery.get(0));
        Assertions.assertEquals(order2,nextDelivery.get(1));
        Assertions.assertEquals(order3,nextDelivery.get(2));

    }
}