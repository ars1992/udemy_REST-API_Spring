package com.example.udemy_REST_API.service;

import com.example.udemy_REST_API.exeception.CustomerServiceException;
import com.example.udemy_REST_API.model.Customer;
import com.example.udemy_REST_API.reposetory.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    public Customer createCustomer(Customer customer) {
        customer.setId(UUID.randomUUID().toString());
        return customerRepository.save(customer);
    }

    public Customer getCustomerById(String id) {
        return customerRepository.findById(id).orElseThrow(CustomerServiceException::new);
    }
}
