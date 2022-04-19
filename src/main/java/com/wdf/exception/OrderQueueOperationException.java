package com.wdf.exception;

public class OrderQueueOperationException extends RuntimeException {

    public OrderQueueOperationException(String message){
        super(message);
    }
}
