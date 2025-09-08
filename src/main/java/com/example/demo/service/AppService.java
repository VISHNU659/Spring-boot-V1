package com.example.demo.service;

import com.example.demo.DTO.CustomerCreationDto;
import com.example.demo.DTO.CustomerDto;
import com.example.demo.entity.Customer;

public interface AppService {
    public Customer createCustomer(CustomerCreationDto customerCreationDto);
}
