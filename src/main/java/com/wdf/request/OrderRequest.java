package com.wdf.request;

import com.wdf.dto.ItemDto;
import com.wdf.entity.Item;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@AllArgsConstructor
public class OrderRequest {

    @NotNull(message = "clientId can not be null")
    private Integer clientId;

    private List<ItemDto> items;
}
