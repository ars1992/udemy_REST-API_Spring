package com.example.udemy_REST_API.exeception;

import lombok.Getter;

@Getter
public class CustomerServiceException extends RuntimeException {
    private final ErrorCode ERROR_CODE;

    public CustomerServiceException(ErrorCode errorCode, String msg){
        super(msg);
        this.ERROR_CODE = errorCode;
    }
}
