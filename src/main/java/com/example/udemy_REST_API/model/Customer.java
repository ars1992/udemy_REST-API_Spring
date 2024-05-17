package com.example.udemy_REST_API.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "TBL_CUSTOMER")
@Data
public class Customer {

    @Id
    private String id;

    private String firstname;

    private String lastname;

    private String email;
}
