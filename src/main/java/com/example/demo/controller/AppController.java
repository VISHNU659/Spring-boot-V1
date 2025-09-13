package com.example.demo.controller;


import com.example.demo.DTO.BondCreationDto;
import com.example.demo.DTO.CustomerCreationDto;
import com.example.demo.DTO.CustomerDto;
import com.example.demo.DTO.LiabilitiesCreationDto;
import com.example.demo.entity.Customer;
import com.example.demo.entity.Liabilities;
import com.example.demo.service.AppServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

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

    @PostMapping("/purchaseBond")
    public ResponseEntity<String> purchaseBond(@RequestBody BondCreationDto bondCreationDto) {

        // Rules : 1. to Check if the Customer Exists , 2. Check if Liability Type is bond , 3. check maturity Date > Creation Date (LocalDate.now())
        CustomerDto customerDto = new CustomerDto();
        customerDto.setId(bondCreationDto.getCustomerId());

        if(!appService.customerExistenceValidation(customerDto))
        {
            return new ResponseEntity<String>("Customer does not exist", HttpStatus.NOT_FOUND);
        }

        if(!bondCreationDto.getLiabilityType().equals("bond"))
        {
            return new ResponseEntity<>("Liability type is not bond", HttpStatus.BAD_REQUEST);
        }

        if(!bondCreationDto.getMaturityDate().isAfter(LocalDate.now()))
        {
            return new ResponseEntity<>("Maturity Date is not after Creation Date(Now/Today)", HttpStatus.BAD_REQUEST);
        }

        Liabilities liabilities = appService.pruchaseBond(bondCreationDto);

        return new ResponseEntity<>("Bond Purchased. \nLiability Id : " + liabilities.getLiabilityId(),HttpStatus.OK);
    }

    @PatchMapping("/depositCash")
    public ResponseEntity<String> depositCash(@RequestParam("liabilityId") int liabilityId, @RequestParam("amount") Double amount) {

        if(!appService.liabilityExistenceValidation(liabilityId))
        {
            return new ResponseEntity<>("Liability Id does not exist", HttpStatus.NOT_FOUND);
        }

        if(!appService.cashDeposit(liabilityId, amount))
        {
            return new ResponseEntity<>("Deposit failed", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>("Deposited successfully", HttpStatus.OK);
    }

    @PatchMapping("/withdrawCash")
    public ResponseEntity<String> withdrawCash(@RequestParam("liabilityId") int liabilityId, @RequestParam("amount") Double amount) {

        if(!appService.liabilityExistenceValidation(liabilityId))
        {
            return new ResponseEntity<>("Liability Id does not exist", HttpStatus.NOT_FOUND);
        }

        String result = appService.cashWithdrawal(liabilityId,amount);

        if(result.equals("InsufficientFunds"))
        {
            return new ResponseEntity<>("InsufficientFunds",HttpStatus.BAD_REQUEST);
        }

        if(result.equals("BankInsolvent"))
        {
            return new ResponseEntity<>("BankInsolvent",HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if(result.equals("Success"))
        {
            return new ResponseEntity<>("Successful Withdrawal",HttpStatus.OK);
        }

        return new ResponseEntity<>("Unknown Error",HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping("/sellBond")
    public ResponseEntity<String> sellBond(@RequestParam("customerId") int customerId, @RequestParam("liabilityId") int liabilityId) {
        CustomerDto customerDto = new CustomerDto();
        customerDto.setId(customerId);

        if(!appService.customerExistenceValidation(customerDto))
        {
            return new ResponseEntity<>("Customer does not exist", HttpStatus.NOT_FOUND);
        }

        if(!appService.liabilityExistenceValidation(liabilityId))
        {
            return new ResponseEntity<>("Liability Id does not exist", HttpStatus.NOT_FOUND);
        }

        if(!appService.customerLiabilityExistenceValidation(customerId,liabilityId))
        {
            return  new ResponseEntity<>("liability ID Mismatch : Does Not Have Required Permissions", HttpStatus.BAD_REQUEST);
        }

        if(!appService.liabilityIsBondCheck(liabilityId))
        {
            return new ResponseEntity<>("liability ID Not of a Bond.", HttpStatus.BAD_REQUEST);
        }

        if(appService.liabilityMaturityValidation(liabilityId).equals("false"))
        {
            return new ResponseEntity<>("Liability Maturity Validation Failed",HttpStatus.BAD_REQUEST);
        }
        else if(appService.liabilityMaturityValidation(liabilityId).equals("true"))
        {
            if(appService.sellBond(liabilityId))
            {
                return new ResponseEntity<>("Bond Sold",HttpStatus.OK);
            }
            else{
                return  new ResponseEntity<>("Unknown Error",HttpStatus.INTERNAL_SERVER_ERROR);
            }

        }

        return  new ResponseEntity<>("Unknown Error",HttpStatus.INTERNAL_SERVER_ERROR);


    }
}
