package com.example.demo.service;

import com.example.demo.DTO.CustomerCreationDto;
import com.example.demo.DTO.CustomerDto;
import com.example.demo.DTO.LiabilitiesCreationDto;
import com.example.demo.DTO.LiabilitiesDto;
import com.example.demo.entity.Customer;
import com.example.demo.entity.Liabilities;
import com.example.demo.mapper.CustomerMapper;
import com.example.demo.mapper.LiabilitiesMapper;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.repository.LiabilitiesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Service
public class AppServiceImpl implements AppService {

    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    LiabilitiesRepository liabilitiesRepository;

    public Customer createCustomer(CustomerCreationDto customerCreationDto) {
        Customer customer = CustomerMapper.toCustomer(customerCreationDto);
        Customer savedCustomer = null;
        try {
            savedCustomer = customerRepository.save(customer);
        } catch (Exception e) {
            System.out.println("-----------------------------------STACK TRACE START----------------------------------------");
            System.out.println(e.getMessage());
            System.out.println("-----------------------------------STACK TRACE END------------------------------------------");
            System.out.println("ERROR MESSAGE : createCustomer threw an exception");
        }
        return savedCustomer;
    }

    public boolean customerExistenceValidation(CustomerDto customerDto) {
        boolean result = false;
        if(customerRepository.findById(customerDto.getId()).isPresent()) // There is a Match for the Provided Id
        {
            result =  true;
        }
        return result;
    }

    public boolean cashDepositExistenceValidation(CustomerDto customerDto,LiabilitiesCreationDto liabilitiesCreationDto) {
        Liabilities liabilities = LiabilitiesMapper.toLiabilities(liabilitiesCreationDto);
        if(!liabilitiesRepository.findByCustomerIdAndLiabilityType(customerDto.getId(),liabilities.getLiabilityType()).isEmpty())
        {
            System.out.println("We entered True");
            return true;
        }
        return false;
    }

    public Liabilities createCashDepositAccount(LiabilitiesCreationDto  liabilitiesCreationDto) {
        Liabilities liabilities = LiabilitiesMapper.toLiabilities(liabilitiesCreationDto);
        Liabilities savedLiabilities = null;

        try {
            savedLiabilities = liabilitiesRepository.save(liabilities);
        } catch (Exception e) {
            System.out.println("-----------------------------------STACK TRACE START----------------------------------------");
            System.out.println(e.getMessage());
            System.out.println("-----------------------------------STACK TRACE END------------------------------------------");
            System.out.println("ERROR MESSAGE : createCashDepositAccount threw an exception");
        }

        return savedLiabilities;
    }



}
