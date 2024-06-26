package com.example.udemy_REST_API.controller;


import com.example.udemy_REST_API.mapper.CustomerMapper;
import com.example.udemy_REST_API.model.Customer;
import com.example.udemy_REST_API.request.CustomerCreateRequest;
import com.example.udemy_REST_API.request.CustomerPartUpdateRequest;
import com.example.udemy_REST_API.request.CustomerUpdateRequest;
import com.example.udemy_REST_API.response.CustomerResponse;
import com.example.udemy_REST_API.response.ErrorResponse;
import com.example.udemy_REST_API.response.ListResponse;
import com.example.udemy_REST_API.service.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
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
    public ResponseEntity<CustomerResponse> createCustomer(@RequestBody @Valid CustomerCreateRequest request){
        Customer customer = customerMapper.mapToModel(request);
        Customer createdCustomer = customerService.createCustomer(customer);
        CustomerResponse customerResponse = customerMapper.mapToResponse(createdCustomer);
        return new ResponseEntity<>(customerResponse, HttpStatus.CREATED);
    }

    @Operation(summary = "Get a customer by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the customer",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CustomerResponse.class)) }),
            @ApiResponse(responseCode = "404", description = "Found no customer",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)) })
    })
    @GetMapping("/{id}")
    public ResponseEntity<CustomerResponse> getCustomerId(@PathVariable String id){
        Customer customer = customerService.getCustomerById(id);
        CustomerResponse response = customerMapper.mapToResponse(customer);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<ListResponse<CustomerResponse>> getCustomers(Integer page, Integer size){
        Page<Customer> pageCustomers = customerService.getAllCustomers(page, size);
        List<CustomerResponse> list = customerMapper.mapToListResponse(pageCustomers.toList());
        ListResponse<CustomerResponse> response = new ListResponse<>(list, pageCustomers.stream().count());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerResponse> updateCustomer(@PathVariable String id, @RequestBody @Valid CustomerUpdateRequest request){
        Customer customer = customerMapper.mapToModel(request);
        Customer updatedCustomer = customerService.updateCustomer(id, customer);
        CustomerResponse response = customerMapper.mapToResponse(updatedCustomer);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<CustomerResponse> updatePartCustomer(@PathVariable String id, @RequestBody @Valid CustomerPartUpdateRequest request){
        Customer customer = customerMapper.mapToModel(request);
        Customer updatedCustomer = customerService.updatePartCustomer(id, customer);
        CustomerResponse response = customerMapper.mapToResponse(updatedCustomer);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable String id){
        customerService.deleteCustomerById(id);
        return  new ResponseEntity<>(HttpStatus.OK);
    }
}
