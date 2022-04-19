package com.wdf.objectTransfer;

import com.wdf.dto.ItemDto;
import com.wdf.entity.Item;
import com.wdf.entity.ItemFactory;

public class ItemTransfer {

    public static Item tansferDtoToDo(ItemDto itemDto){
        Item item = new ItemFactory().createItem(itemDto.getType(), itemDto.getQuantity());
        return item;
    }
}
