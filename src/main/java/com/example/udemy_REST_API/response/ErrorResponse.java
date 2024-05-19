package com.example.udemy_REST_API.response;

import com.example.udemy_REST_API.exeception.ErrorCode;
import lombok.Getter;

@Getter
public class ErrorResponse {
    private String message;
    private ErrorCode errorCode;

    public ErrorResponse() {
    }

    public ErrorResponse(String message, ErrorCode errorCode) {

        this.message = message;

        this.errorCode = errorCode;
    }
}
