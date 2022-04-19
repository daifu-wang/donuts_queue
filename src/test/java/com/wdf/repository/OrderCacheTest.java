package com.wdf.repository;

import com.wdf.entity.Order;
import com.wdf.tool.TestDataCreator;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class OrderCacheTest {
    private OrderCache orderCache;
    Order order = TestDataCreator.createOrderWithOneItem(1,1,8);

    @BeforeEach
    void beforeEach(){
        orderCache = new OrderCache();
        orderCache.putIfAbsent(1,order);
    }

    @AfterEach
    void afterEach(){
        orderCache = new OrderCache();
    }

    @Test
    void putIfAbsent() {

        Order order = TestDataCreator.createOrderWithOneItem(3,1,8);
        orderCache.putIfAbsent(3,order);
        orderCache.putIfAbsent(3,order);

        assertEquals(2,orderCache.size());
    }

    @Test
    void get() {
        assertEquals(order,orderCache.get(1));
    }

    @Test
    void contains() {
        assertEquals(true,orderCache.contains(1));
    }

    @Test
    void delete() {
        orderCache.delete(1);
        assertEquals(0,orderCache.size());
    }
}