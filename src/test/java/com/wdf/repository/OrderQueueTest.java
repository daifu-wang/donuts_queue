package com.wdf.repository;

import com.wdf.entity.Item;
import com.wdf.entity.ItemFactory;
import com.wdf.entity.Order;
import com.wdf.enums.ItemEnum;
import com.wdf.service.impl.MockPremiumClientServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class OrderQueueTest {


    private OrderQueue orderQueue = new OrderQueue(new MockPremiumClientServiceImpl());
    Order order1 = createOrder(1);
    Order order2 = createOrder(2);
    Order order3 = createOrder(3);
    Order order4 = createOrder(1111);
    Order order5 = createOrder(3333);

    @BeforeEach
    void beforeEach(){

        orderQueue.offerOrder(order1);
        orderQueue.offerOrder(order2);
        orderQueue.offerOrder(order3);
    }

    @AfterEach
    void afterEach(){
        OrderQueue orderQueue = new OrderQueue(new MockPremiumClientServiceImpl());
    }

    @Test
    void addOrder() {
        Order order6 = createOrder(6);

        orderQueue.offerOrder(order6);

        assertEquals(order1,orderQueue.poll().get());
        assertEquals(order2,orderQueue.poll().get());
        assertEquals(order3,orderQueue.poll().get());
        assertEquals(order6,orderQueue.poll().get());

        assertFalse(orderQueue.poll().isPresent());
    }

    @Test
    void pollAndPeek() {

        Order orderPeek1 = orderQueue.peek().get();
        Order orderPool1 = orderQueue.poll().get();

        Order orderPeek2 = orderQueue.peek().get();
        Order orderPool2 = orderQueue.poll().get();

        Order orderPeek3 = orderQueue.peek().get();
        Order orderPool3 = orderQueue.poll().get();


        assertEquals(orderPeek1, orderPool1);
        assertEquals(orderPeek2, orderPool2);
        assertEquals(orderPeek3, orderPool3);

        assertEquals(orderPool1, order1);
        assertEquals(orderPool2, order2);
        assertEquals(orderPool3, order3);

        assertFalse(orderQueue.peek().isPresent());
        assertFalse(orderQueue.poll().isPresent());


    }

    private Order createOrder(Integer clientId) {
        ArrayList<Item> items = new ArrayList<>();
        Item donut = new ItemFactory().createItem(ItemEnum.DONUT.getType(), 6);
        Item cake = new ItemFactory().createItem(ItemEnum.CAKE.getType(), 5);
        items.add(donut);
        items.add(cake);
        return new Order(clientId,11, LocalDateTime.now(),items);

    }

}