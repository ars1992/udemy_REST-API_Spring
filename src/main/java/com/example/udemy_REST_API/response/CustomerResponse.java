package com.example.udemy_REST_API.response;

import lombok.Data;

@Data
public class CustomerResponse {
    private String id;
    private String firstname;
    private String lastname;
    private String email;
}
