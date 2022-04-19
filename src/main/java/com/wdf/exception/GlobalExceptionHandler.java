package com.wdf.exception;

import com.wdf.response.CommonResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice(basePackages="com.wdf.controller")
public class GlobalExceptionHandler {

    @ResponseBody
    @ExceptionHandler(value = OrderQueueOperationException.class )
    public CommonResponse handleException(OrderQueueOperationException e){

        e.printStackTrace();

        return new CommonResponse(400,e.getMessage());
    }

    @ResponseBody
    @ExceptionHandler(value = IllegalArgumentException.class )
    public CommonResponse handleException(IllegalArgumentException e){

        e.printStackTrace();

        return new CommonResponse(400,e.getMessage());
    }

    @ResponseBody
    @ExceptionHandler(value = Exception.class )
    public CommonResponse handleException(Exception e){

        e.printStackTrace();

        return new CommonResponse(500,"internal error");
    }
}
