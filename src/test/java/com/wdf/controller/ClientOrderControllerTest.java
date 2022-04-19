package com.wdf.controller;

import com.wdf.entity.Order;
import com.wdf.response.CommonResponse;
import com.wdf.service.ClientOrderService;
import com.wdf.tool.TestDataCreator;
import com.wdf.vo.OrderVo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ClientOrderControllerTest {

    @InjectMocks
    ClientOrderController controller ;
    @Mock
    private ClientOrderService orderService;

    @Test
    void order() throws Exception {

        CommonResponse<OrderVo> response1 = controller.order(-1, 1);
        CommonResponse<OrderVo> response2 = controller.order(1, -1);

        Assertions.assertEquals(400,response1.getCode().intValue());
        Assertions.assertEquals(400,response2.getCode().intValue());

        Order order = TestDataCreator.createOrderWithOneItem(1, 1,10);

        when(orderService.order(any(Order.class))).thenReturn(order);

        CommonResponse<OrderVo> response = controller.order(1, 10);
        Assertions.assertEquals(200,response.getCode().intValue());
        Assertions.assertEquals(1,response.getResult().getClientId().intValue());
        Assertions.assertTrue(response.getResult().getWaitingSeconds() >= 1);

    }

    @Test
    void getOrderInfo() {

        Order order = TestDataCreator.createOrderWithOneItem(1, 1, 3);

        when(orderService.getMyOrderInfo(1)).thenReturn(Optional.of(order));

        CommonResponse<OrderVo> response = controller.getOrderInfo(1);

        Assertions.assertEquals(200,response.getCode().intValue());
        Assertions.assertTrue(response.getResult().getWaitingSeconds() >=1);
        Assertions.assertTrue(response.getResult().getItems().size() ==1);

        when(orderService.getMyOrderInfo(1)).thenReturn(Optional.empty());
        CommonResponse<OrderVo> response1 = controller.getOrderInfo(1);
        Assertions.assertEquals(400,response1.getCode().intValue());
    }

    @Test
    void cancelOrder() {

        when(orderService.cancelOrder(1)).thenReturn(true);
        CommonResponse response = controller.cancelOrder(1);
        Assertions.assertEquals(200,response.getCode().intValue());

        when(orderService.cancelOrder(1)).thenReturn(false);
        CommonResponse response1 = controller.cancelOrder(1);
        Assertions.assertEquals(400,response1.getCode().intValue());
    }
}