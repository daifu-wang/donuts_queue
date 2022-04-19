package com.wdf.objectTransfer;

import com.wdf.entity.Item;
import com.wdf.entity.Order;
import com.wdf.vo.OrderVo;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

public class OrderVoTransfer {

    public static OrderVo transfer(Order order){

        Integer clientId = order.getClientId();
        Integer totalQuantity = order.getTotalQuantity();
        List<Item> items = order.getItems();
        LocalDateTime createTime = order.getCreateTime();
        Duration duration = Duration.between(createTime,LocalDateTime.now());
        int waitingSeconds = (int) duration.getSeconds() + 1;

        return new OrderVo(clientId, totalQuantity, items, createTime, waitingSeconds);
    }
}
