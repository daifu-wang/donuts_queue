package com.wdf.controller;

import com.wdf.entity.Order;
import com.wdf.objectTransfer.OrderVoTransfer;
import com.wdf.response.CommonResponse;
import com.wdf.service.ClientOrderService;
import com.wdf.service.EmployeeOrderService;
import com.wdf.tool.TestDataCreator;
import com.wdf.vo.OrderVo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EmployeeOrderControllerTest {

    @InjectMocks
    EmployeeOrderController controller ;
    @Mock
    private EmployeeOrderService orderService;

    @Test
    void getAllOrders() {

        Order order1 = TestDataCreator.createOrderWithOneItem(1, 1, 5);
        Order order2 = TestDataCreator.createOrderWithOneItem(2,1,4);
        ArrayList<Order> orders = new ArrayList<>();
        orders.add(order1);
        orders.add(order2);

        when(orderService.getAllOrders()).thenReturn(orders);

        CommonResponse<List<OrderVo>> response = controller.getAllOrders();

        Assertions.assertEquals(200,response.getCode().intValue());

        List<OrderVo> orderVos = response.getResult();

        Assertions.assertEquals(2,orderVos.size());

        Assertions.assertEquals(1,orderVos.get(0).getClientId().intValue());
        Assertions.assertEquals(2,orderVos.get(1).getClientId().intValue());

    }

    @Test
    void getNextDelivery() {

        Order order1 = TestDataCreator.createOrderWithOneItem(1, 1, 10);
        Order order2 = TestDataCreator.createOrderWithOneItem(2,1,9);
        Order order3 = TestDataCreator.createOrderWithOneItem(3,1,8);
        Order order4 = TestDataCreator.createOrderWithOneItem(4,1,7);
        Order order5 = TestDataCreator.createOrderWithOneItem(5,1,6);
        ArrayList<Order> orders = new ArrayList<>();
        orders.add(order1);
        orders.add(order2);
        orders.add(order3);
        orders.add(order4);
        orders.add(order5);

        when(orderService.getNextDelivery()).thenReturn(orders);

        CommonResponse<List<OrderVo>> response = controller.getNextDelivery();

        Assertions.assertEquals(200,response.getCode().intValue());
        List<OrderVo> orderVos = response.getResult();
        Assertions.assertEquals(5,orderVos.size());

        Assertions.assertEquals(10,orderVos.get(0).getTotalQuantity().intValue());
        Assertions.assertEquals(9,orderVos.get(1).getTotalQuantity().intValue());
        Assertions.assertEquals(8,orderVos.get(2).getTotalQuantity().intValue());
        Assertions.assertEquals(7,orderVos.get(3).getTotalQuantity().intValue());
        Assertions.assertEquals(6,orderVos.get(4).getTotalQuantity().intValue());
    }
}