package com.wdf.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.Min;

@Data
@AllArgsConstructor
public abstract class Item {

    private final String name;

    private final Integer quantity;

}
