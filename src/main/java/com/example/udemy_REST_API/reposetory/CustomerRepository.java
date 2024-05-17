package com.example.udemy_REST_API.reposetory;

import com.example.udemy_REST_API.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, String> {
}
