package com.example.udemy_REST_API.mapper;

import com.example.udemy_REST_API.model.Customer;
import com.example.udemy_REST_API.request.CustomerCreateRequest;
import com.example.udemy_REST_API.response.CustomerResponse;
import org.mapstruct.Mapper;

@Mapper
public interface CustomerMapper {
    Customer mapToModel(CustomerCreateRequest request);

    CustomerResponse mapToResponse(Customer customer);
}
