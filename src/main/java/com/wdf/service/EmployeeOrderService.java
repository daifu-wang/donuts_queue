package com.wdf.service;

import com.wdf.entity.Item;
import com.wdf.entity.Order;

import java.util.List;

public interface EmployeeOrderService {

    List<Order> getAllOrders();

    List<Order> getNextDelivery();

}
