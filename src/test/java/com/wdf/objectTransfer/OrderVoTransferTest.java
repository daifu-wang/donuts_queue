package com.wdf.objectTransfer;

import com.wdf.entity.Item;
import com.wdf.entity.ItemFactory;
import com.wdf.entity.Order;
import com.wdf.enums.ItemEnum;
import com.wdf.vo.OrderVo;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OrderVoTransferTest {


    @Test
    void testTransfer(){
        List<Item> list = new ArrayList<>();
        Item item = new ItemFactory().createItem(ItemEnum.DONUT.getType(), 3);
        list.add(item);

        Order order = new Order(1,3,LocalDateTime.now(),list);

        OrderVo transfer = OrderVoTransfer.transfer(order);

        assertEquals(list,transfer.getItems());
        assertEquals(0,transfer.getWaitingSeconds().intValue());
        assertEquals(1,transfer.getClientId().intValue());
    }
}