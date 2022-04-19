package com.wdf.service.impl;

import com.wdf.entity.Order;
import com.wdf.exception.OrderQueueOperationException;
import com.wdf.repository.OrderCache;
import com.wdf.repository.OrderQueue;
import com.wdf.tool.TestDataCreator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ClientOrderServiceImplTest {

    @InjectMocks
    ClientOrderServiceImpl service;
    @Mock
    OrderCache orderCache;
    @Mock
    OrderQueue orderQueue;

    @Test
    void order() {

        Order order = TestDataCreator.createOrderWithOneItem(1, 1, 5);

        when(orderCache.putIfAbsent(1,order)).thenReturn(null);
        when(orderQueue.offerOrder(order)).thenReturn(true);

        Assertions.assertEquals(order,service.order(order));

        when(orderCache.putIfAbsent(1,order)).thenReturn(order);
        when(orderQueue.offerOrder(order)).thenReturn(true);
        Assertions.assertThrows(OrderQueueOperationException.class,()->service.order(order));

        when(orderCache.putIfAbsent(1,order)).thenReturn(null);
        when(orderQueue.offerOrder(order)).thenReturn(false);
        Assertions.assertThrows(OrderQueueOperationException.class,()->service.order(order));
    }

    @Test
    void cancelOrder() {

        when(orderCache.get(1)).thenReturn(null);
        Assertions.assertThrows(OrderQueueOperationException.class,()->service.cancelOrder(1));
        Order order = TestDataCreator.createOrderWithOneItem(1, 1, 5);
        when(orderCache.get(1)).thenReturn(order);
        when(orderCache.delete(1)).thenReturn(order);
        Assertions.assertEquals(true,service.cancelOrder(1));
    }

    @Test
    void getMyOrderInfo() {
        Order order = TestDataCreator.createOrderWithOneItem(1, 1, 5);
        when(orderCache.get(1)).thenReturn(order);

        Assertions.assertTrue(service.getMyOrderInfo(1).isPresent());

        when(orderCache.get(1)).thenReturn(null);
        Assertions.assertFalse(service.getMyOrderInfo(1).isPresent());
    }
}