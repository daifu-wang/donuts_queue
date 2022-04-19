package com.wdf.vo;

import com.wdf.entity.Item;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Data
@AllArgsConstructor
public  class OrderVo {

    private final Integer clientId;
    private final Integer totalQuantity;

    private final List<Item> items;

    private final LocalDateTime createTime;

    private final Integer waitingSeconds;

}
