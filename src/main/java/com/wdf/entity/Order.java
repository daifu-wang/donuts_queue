package com.wdf.entity;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Data
public  class Order {

    private final Integer clientId;

    private final List<Item> items;

    private final Integer totalQuantity;

    private final LocalDateTime createTime;

    private final Boolean NOT_CANCELED = false;

    private Boolean isCanceled;

    public Order(Integer clientId, Integer totalQuantity,LocalDateTime dateTime,List<Item> items) {
        this.clientId = clientId;
        this.items = items;
        this.totalQuantity= totalQuantity;
        this.createTime = dateTime;
        this.isCanceled = NOT_CANCELED;
    }

    public Boolean isCanceled(){
        return isCanceled;
    }
}
