package com.example.udemy_REST_API.service;

import com.example.udemy_REST_API.exeception.CustomerServiceException;
import com.example.udemy_REST_API.model.Customer;
import com.example.udemy_REST_API.reposetory.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
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

    public Page<Customer> getAllCustomers(Integer page, Integer size) {
        return customerRepository.findAll(PageRequest.of(page, size));
    }

    public Customer updateCustomer(String id, Customer customer) {
        Customer oldCustomer = this.getCustomerById(id);
        customer.setId(oldCustomer.getId());
        customer.setEmail(oldCustomer.getEmail());
        return customerRepository.save(customer);
    }

    public Customer updatePartCustomer(String id, Customer customer) {
        Customer oldCustomer = this.getCustomerById(id);

        if (customer.getFirstname() != null && ! customer.getFirstname().isBlank()){
            oldCustomer.setFirstname(customer.getFirstname());
        }

        if (customer.getLastname() != null && ! customer.getLastname().isBlank()){
            oldCustomer.setLastname(customer.getLastname());
        }

        return oldCustomer;
    }

    public void deleteCustomerById(String id) {
        Customer customer = this.getCustomerById(id);
        customerRepository.delete(customer);
    }
}
