package com.example.udemy_REST_API.request;

import lombok.Data;

@Data
public class CustomerUpdateRequest {
    private String firstname;
    private String lastname;
}
