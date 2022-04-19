package com.wdf.controller;

import com.wdf.entity.Order;
import com.wdf.objectTransfer.OrderVoTransfer;
import com.wdf.response.CommonResponse;
import com.wdf.service.EmployeeOrderService;
import com.wdf.vo.OrderVo;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/employee/orders")
public class EmployeeOrderController {
    private final EmployeeOrderService orderService;

    public EmployeeOrderController(EmployeeOrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public CommonResponse<List<OrderVo>> getAllOrders() {
        List<Order> orders = orderService.getAllOrders();

        List<OrderVo> orderVos = orders.stream().map(OrderVoTransfer::transfer).collect(Collectors.toList());

        return new CommonResponse<>(200, "success", orderVos);
    }

    @DeleteMapping
    public CommonResponse<List<OrderVo>> getNextDelivery() {
        List<Order> orders = orderService.getNextDelivery();
        List<OrderVo> orderVos = orders.stream().map(OrderVoTransfer::transfer).collect(Collectors.toList());
        return new CommonResponse<>(200,"success",orderVos);
    }
}
