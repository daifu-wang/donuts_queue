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
    Order order4 = createOrder(4);
    Order order5 = createOrder(10000);
    Order order6 = createOrder(5);

    @BeforeEach
    void beforeEach(){

        orderQueue.offerOrder(order1);
        orderQueue.offerOrder(order2);
        orderQueue.offerOrder(order3);
        orderQueue.offerOrder(order4);
        orderQueue.offerOrder(order5);
        orderQueue.offerOrder(order6);
    }

    @AfterEach
    void afterEach(){
        OrderQueue orderQueue = new OrderQueue(new MockPremiumClientServiceImpl());
    }

    @Test
    void addOrder() {
        Order order7 = createOrder(1111);

        orderQueue.offerOrder(order7);

        assertEquals(5,orderQueue.premiumQueueSize());
        assertEquals(2,orderQueue.normalQueueSize());
    }

    @Test
    void pollAndPeek() {

        Order orderPeek1 = orderQueue.peek().get();
        Order orderPool1 = orderQueue.poll().get();

        Order orderPeek2 = orderQueue.peek().get();
        Order orderPool2 = orderQueue.poll().get();

        Order orderPeek3 = orderQueue.peek().get();
        Order orderPool3 = orderQueue.poll().get();

        Order orderPeek4= orderQueue.peek().get();
        Order orderPool4 = orderQueue.poll().get();

        Order orderPeek5 = orderQueue.peek().get();
        Order orderPool5 = orderQueue.poll().get();

        Order orderPeek6 = orderQueue.peek().get();
        Order orderPool6 = orderQueue.poll().get();


        assertEquals(orderPeek1, orderPool1);
        assertEquals(orderPeek2, orderPool2);
        assertEquals(orderPeek3, orderPool3);
        assertEquals(orderPeek4, orderPool4);
        assertEquals(orderPeek5, orderPool5);
        assertEquals(orderPeek6, orderPool6);

        assertEquals(orderPool1, order1);
        assertEquals(orderPool2, order2);
        assertEquals(orderPool3, order3);
        assertEquals(orderPool4, order4);
        assertEquals(orderPool5, order6);
        assertEquals(orderPool6, order5);

        assertFalse(orderQueue.peek().isPresent());
        assertFalse(orderQueue.poll().isPresent());


    }

    private Order createOrder(Integer clientId) {
        ArrayList<Item> items = new ArrayList<>();
        Item donut = new ItemFactory().createItem(ItemEnum.DONUT.getType(), 5);
        Item cake = new ItemFactory().createItem(ItemEnum.CAKE.getType(), 5);
        items.add(donut);
        items.add(cake);
        return new Order(clientId,10, LocalDateTime.now(),items);

    }

}