package com.example.udemy_REST_API.exeception;



import com.example.udemy_REST_API.response.ErrorResponse;
import org.springframework.http.*;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request
    ){
        List<String> errors = ex.getBindingResult().getAllErrors()
                .stream()
                .map(objectError -> (FieldError) objectError)
                .map(fieldError -> fieldError.getField() + " - " + fieldError.getDefaultMessage())
                .collect(Collectors.toList());
        return handleExceptionInternal(ex, errors, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(CustomerServiceException.class)
    public ResponseEntity<Object> handleCustomerServiceException(CustomerServiceException ex, WebRequest request){
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        ErrorResponse errorResponse = new ErrorResponse("INTERNAL_SERVER_ERROR", ErrorCode.DEFAULT_ERROR);

        if (ex.getERROR_CODE() == ErrorCode.CUSTOMER_NOT_FOUND){
            errorResponse = new ErrorResponse(ex.getMessage(), ex.getERROR_CODE());
            status = HttpStatus.NOT_FOUND;
        }

        if (ex.getERROR_CODE() == ErrorCode.EMAIL_EXISTS){
            errorResponse = new ErrorResponse(ex.getMessage(), ex.getERROR_CODE());
            status = HttpStatus.BAD_REQUEST;
        }

        return handleExceptionInternal(ex, errorResponse, new HttpHeaders(), status, request);
    }
}

