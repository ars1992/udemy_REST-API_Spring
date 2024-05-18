package com.example.udemy_REST_API.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class CustomerCreateRequest {

    @NotEmpty
    private String firstname;

    @NotEmpty
    private String lastname;

    @Email
    @NotEmpty
    private String email;
}
