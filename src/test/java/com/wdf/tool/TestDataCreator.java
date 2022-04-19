package com.wdf.tool;

import com.wdf.entity.Item;
import com.wdf.entity.ItemFactory;
import com.wdf.entity.Order;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class TestDataCreator {

    public static Order createOrderWithOneItem(Integer clientId, int type, int totalQuantity) {
        ArrayList<Item> items = new ArrayList<>();
        Item donut1 = new ItemFactory().createItem(type, totalQuantity);
        items.add(donut1);
        return new Order(clientId, totalQuantity, LocalDateTime.now(),items);

    }
}
