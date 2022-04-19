package com.wdf.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CommonResponse<T> {

    private final Integer code;
    private final String message;
    private final T result;

    public CommonResponse(Integer code, String message) {
        this.code = code;
        this.message = message;
        this.result = null;
    }
}
