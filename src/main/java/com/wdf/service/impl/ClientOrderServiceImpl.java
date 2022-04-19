package com.wdf.service.impl;

import com.wdf.entity.*;
import com.wdf.exception.OrderQueueOperationException;
import com.wdf.repository.OrderCache;
import com.wdf.repository.OrderQueue;
import com.wdf.service.ClientOrderService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ClientOrderServiceImpl implements ClientOrderService {

    private final OrderCache orderCache;
    private final OrderQueue orderQueue;

    private final Boolean CANCELED = true;

    public ClientOrderServiceImpl(OrderCache orderCache, OrderQueue orderQueue) {
        this.orderCache = orderCache;
        this.orderQueue = orderQueue;
    }

    @Override
    public Order order(Order order) {
        Order addedOrder = orderCache.putIfAbsent(order.getClientId(), order);
        if (addedOrder!=null){
            throw new OrderQueueOperationException("already have an order!");
        }
        if(!orderQueue.offerOrder(order)){
            throw new OrderQueueOperationException("No space in the order queue!");
        }
        return order;
    }

    @Override
    public Boolean cancelOrder(Integer clientId) {

        Order order = orderCache.get(clientId);
        if (order==null) {
            throw new OrderQueueOperationException("No order found");
        }
        order.setIsCanceled(CANCELED);
        orderCache.delete(clientId);
        return true;
    }

    @Override
    public Optional<Order> getMyOrderInfo(Integer clientId) {

        return Optional.ofNullable(orderCache.get(clientId));
    }

}
