package com.wdf.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ItemDto {
    private final String name;

    private final Integer quantity;

    private final Integer type;
}
