package com.wdf.entity;

import com.wdf.enums.ItemEnum;

public class ItemFactory extends AbstractItemFactory {
    @Override
    public Item createItem(Integer type, Integer quantity) {

        if (type.equals(ItemEnum.DONUT.getType())) {
            return new Donut(ItemEnum.DONUT.getName(), quantity);
        }
        if (type.equals(ItemEnum.CAKE.getType())) {
            return new Cake(ItemEnum.CAKE.getName(), quantity);
        } else {
            throw new IllegalArgumentException("donut type does not exists!");
        }
    }
}
