package com.example.demo.controller;


import com.example.demo.DTO.CustomerCreationDto;
import com.example.demo.DTO.CustomerDto;
import com.example.demo.DTO.LiabilitiesCreationDto;
import com.example.demo.entity.Customer;
import com.example.demo.entity.Liabilities;
import com.example.demo.service.AppServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/demo1")
public class AppController {

    @Autowired
    AppServiceImpl appService;

    @PostMapping("/createCustomer")
    public ResponseEntity<String> createCustomer(@RequestBody CustomerCreationDto customerCreationDto) {

        Customer customer = appService.createCustomer(customerCreationDto);
        if(customer == null)
        {
            return new ResponseEntity<String>("Customer creation failed", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        else{
            return new ResponseEntity<String>("Customer successfully created \n Account Number : " + customer.getId(), HttpStatus.OK);
        }
    }

    @PostMapping("/createCashDepositAccount")
    public ResponseEntity<String> createCashDepositAccount(@RequestBody LiabilitiesCreationDto  liabilitiesCreationDto) {

        // Rules : 1. To check if given Customer Exists , 2. to Check if given LiabilityType is "cash" , 3. see if the customer already has another Cash deposit account
        CustomerDto customerDto = new CustomerDto();
        customerDto.setId(liabilitiesCreationDto.getCustomerId());

        if(!appService.customerExistenceValidation(customerDto))
        {
            return new ResponseEntity<String>("Customer does not exist", HttpStatus.NOT_FOUND);
        }

        if(!liabilitiesCreationDto.getLiabilityType().equals("cash"))
        {
            return new ResponseEntity<>("Liability type is not cash", HttpStatus.BAD_REQUEST);
        }

        if(appService.cashDepositExistenceValidation(customerDto, liabilitiesCreationDto))
        {
            return new ResponseEntity<String>("Customer already has cash deposit Account", HttpStatus.BAD_REQUEST);
        }

        Liabilities liabilities = appService.createCashDepositAccount(liabilitiesCreationDto);

        return new ResponseEntity<String>("Cash Deposit Account Created. \nLiability Id : " + liabilities.getLiabilityId(),HttpStatus.OK);

    }


}
