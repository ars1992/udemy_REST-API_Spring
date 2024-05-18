package com.example.udemy_REST_API.controller;


import com.example.udemy_REST_API.mapper.CustomerMapper;
import com.example.udemy_REST_API.model.Customer;
import com.example.udemy_REST_API.request.CustomerCreateRequest;
import com.example.udemy_REST_API.response.CustomerResponse;
import com.example.udemy_REST_API.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/customers")
public class CustomerController {

    private final CustomerService customerService;
    private final CustomerMapper customerMapper;

    @PostMapping
    public ResponseEntity<CustomerResponse> createCustomer(@RequestBody CustomerCreateRequest request){
        Customer customer = customerMapper.mapToModel(request);
        Customer createdCustomer = customerService.createCustomer(customer);
        CustomerResponse customerResponse = customerMapper.mapToResponse(createdCustomer);
        return new ResponseEntity<>(customerResponse, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerResponse> getCustomerId(@PathVariable String id){
        Customer customer = customerService.getCustomerById(id);
        CustomerResponse response = customerMapper.mapToResponse(customer);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<CustomerResponse>> getCustomers(){
        List<Customer> customers = customerService.getAllCustomers();
        List<CustomerResponse> response = customerMapper.mapToListResponse(customers);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
