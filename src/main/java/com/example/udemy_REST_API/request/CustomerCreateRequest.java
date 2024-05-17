package com.example.udemy_REST_API.request;

import lombok.Data;

@Data
public class CustomerCreateRequest {
    private String id;
    private String firstname;
    private String lastname;
    private String email;
}
