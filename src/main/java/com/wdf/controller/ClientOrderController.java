package com.wdf.controller;

import com.wdf.entity.Item;
import com.wdf.entity.ItemFactory;
import com.wdf.entity.Order;
import com.wdf.enums.ItemEnum;
import com.wdf.objectTransfer.OrderVoTransfer;
import com.wdf.response.CommonResponse;
import com.wdf.service.ClientOrderService;
import com.wdf.vo.OrderVo;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/client/orders")
public class ClientOrderController {
    private final ClientOrderService orderService;

    public ClientOrderController(ClientOrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/{id}/{quantity}")
    public CommonResponse<OrderVo> order(@PathVariable("id")Integer clientId, @PathVariable("quantity")Integer quantity) {
        if (clientId <= 0 || quantity <= 0) {
            return new CommonResponse<>(400, "illegal parameter");
        }
        Item donut = new ItemFactory().createItem(ItemEnum.DONUT.getType(), quantity);
        List<Item> items = new ArrayList<>();
        items.add(donut);

        Order orderParam = createOrder(clientId, items);

        Order order = orderService.order(orderParam);

        return new CommonResponse<>(200, "success",OrderVoTransfer.transfer(order));
    }

    @GetMapping("/{clientId}")
    public CommonResponse<OrderVo> getOrderInfo(@PathVariable("clientId")Integer clientId) {

        if (clientId <= 0) {
            return new CommonResponse(400, "legal parameter");
        }

        Optional<Order> order = orderService.getMyOrderInfo(clientId);
        if (order.isPresent()){
            return new CommonResponse<>(200, "success", OrderVoTransfer.transfer(order.get()));
        }

        return new CommonResponse<>(400, "No order found", null);
    }

    @PatchMapping("/{clientId}")
    public CommonResponse cancelOrder(@PathVariable("clientId")Integer clientId) {
        if (orderService.cancelOrder(clientId)){
            return new CommonResponse(200, "success");
        }
        return new CommonResponse(400, "No order found");
    }

    private Order createOrder(Integer clientId, List<Item> items) {
        int totalQuantity = items.stream().mapToInt(Item::getQuantity).sum();

        return new Order(clientId, totalQuantity, LocalDateTime.now(),items);
    }

}
