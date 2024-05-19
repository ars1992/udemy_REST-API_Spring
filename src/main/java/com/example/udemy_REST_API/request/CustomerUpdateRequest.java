package com.example.udemy_REST_API.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class CustomerUpdateRequest {

    @NotEmpty
    private String firstname;

    @NotEmpty
    private String lastname;
}
