package com.wdf.service;

import com.wdf.entity.Item;
import com.wdf.entity.Order;
import com.wdf.request.OrderRequest;

import java.util.List;
import java.util.Optional;

public interface ClientOrderService {

    Order order(Order order);

    Boolean cancelOrder(Integer clientId);

    Optional<Order> getMyOrderInfo(Integer clientId);

}
