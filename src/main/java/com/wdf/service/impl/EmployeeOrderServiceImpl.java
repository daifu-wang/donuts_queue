package com.wdf.service.impl;

import com.wdf.entity.Order;
import com.wdf.repository.OrderCache;
import com.wdf.repository.OrderQueue;
import com.wdf.service.EmployeeOrderService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeOrderServiceImpl implements EmployeeOrderService {

    private final OrderCache orderCache;
    private final OrderQueue orderQueue;
    private final Integer CART_CAPACITY = 50;

    public EmployeeOrderServiceImpl(OrderCache orderCache, OrderQueue orderQueue) {
        this.orderCache = orderCache;
        this.orderQueue = orderQueue;
    }

    @Override
    public List<Order> getAllOrders() {
        return orderCache.getAllOrders();
    }

    @Override
    public List<Order> getNextDelivery() {

        int capacity = CART_CAPACITY;
        List<Order> cart = new ArrayList<>();

        while (true) {
            Optional<Order> peekedOrder = orderQueue.peek();
            if (!peekedOrder.isPresent()) {
                break;
            }

            if (peekedOrder.get().isCanceled()){
                orderQueue.poll();
                continue;
            }

            Order order = peekedOrder.get();
            if (capacity - order.getTotalQuantity() >= 0) {
                capacity -= order.getTotalQuantity();
                cart.add(orderQueue.poll().get());
                orderCache.delete(order.getClientId());
            } else {
                break;
            }
        }
        return cart;
    }
}
