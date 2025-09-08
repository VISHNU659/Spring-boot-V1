package com.example.demo.mapper;

import com.example.demo.DTO.CustomerCreationDto;
import com.example.demo.DTO.CustomerDto;
import com.example.demo.entity.Customer;

public class CustomerMapper {

    public static Customer toCustomer(CustomerDto customerDto) {
        Customer customer = new Customer();

        customer.setId(customerDto.getId());
        customer.setName(customerDto.getName());
        customer.setEmail(customerDto.getEmail());

        return customer;
    }

    public static Customer toCustomer(CustomerCreationDto customerCreationDto) {
        Customer customer = new Customer();

        customer.setName(customerCreationDto.getName());
        customer.setEmail(customerCreationDto.getEmail());

        return customer;
    }

    public static CustomerDto toCustomerDto(Customer customer) {
        CustomerDto customerDto = new CustomerDto();

        customerDto.setId(customer.getId());
        customerDto.setName(customer.getName());
        customerDto.setEmail(customer.getEmail());

        return customerDto;
    }
}
