package com.example.demo.service;

import com.example.demo.DTO.BondCreationDto;
import com.example.demo.DTO.CustomerCreationDto;
import com.example.demo.DTO.CustomerDto;
import com.example.demo.DTO.LiabilitiesCreationDto;
import com.example.demo.entity.BankAssets;
import com.example.demo.entity.Customer;
import com.example.demo.entity.Liabilities;
import com.example.demo.enums.LiabilityType;
import com.example.demo.exception.InsufficientFundsException;
import com.example.demo.mapper.CustomerMapper;
import com.example.demo.mapper.LiabilitiesMapper;
import com.example.demo.repository.BankAssetsRepository;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.repository.LiabilitiesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class AppServiceImpl implements AppService {

    private static final int BANK_ASSET_ID = 1;
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    LiabilitiesRepository liabilitiesRepository;
    @Autowired
    BankAssetsRepository bankAssetsRepository;

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

    public boolean liabilityExistenceValidation(int liabilityId) {
        boolean result = false;
        if(liabilitiesRepository.findById(liabilityId).isPresent()) // There is a Match for the Provided Id
        {
            result =  true;
        }
        return result;
    }

    public boolean customerLiabilityExistenceValidation(int customerId , int liabilityId) {
        boolean result = false;
        if(liabilitiesRepository.findById(liabilityId).get().getCustomerId() == customerId)
        {
            result = true;
        }
        return result;
    }

    public boolean liabilityIsBondCheck(int liabilityId)
    {
        boolean result = false;
        if(liabilitiesRepository.findById(liabilityId).get().getLiabilityType() == LiabilityType.bond)
        {
            result = true;
        }
        return result;
    }

    public String liabilityMaturityValidation(int liabilityId) {

        LocalDate maturityDate = liabilitiesRepository.findById(liabilityId).get().getMaturityDate();
        if(maturityDate == null)
        {
            return "null";
        }
        else if(maturityDate.isBefore(LocalDate.now()))
        {
            return "true";
        }
        else{
            return "false";
        }
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

    public Liabilities pruchaseBond(BondCreationDto bondCreationDto) {
        Liabilities liabilities = LiabilitiesMapper.toLiabilities(bondCreationDto);
        Liabilities savedLiabilities = null;

        try{
            savedLiabilities = liabilitiesRepository.save(liabilities);
        }catch(Exception e){
            System.out.println("-----------------------------------STACK TRACE START----------------------------------------");
            System.out.println(e.getMessage());
            System.out.println("-----------------------------------STACK TRACE END------------------------------------------");
            System.out.println("ERROR MESSAGE : purchaseBond : Saving Liability :threw an exception");
        }

        try{
            BankAssets bankAssets = bankAssetsRepository.getReferenceById(BANK_ASSET_ID);
            bankAssets.setCashValue(bankAssets.getCashValue() + liabilities.getLiabilityValue());
            bankAssets.setLastUpdate(LocalDate.now());
            bankAssetsRepository.save(bankAssets);
        }catch(Exception e){
            System.out.println("-----------------------------------STACK TRACE START----------------------------------------");
            System.out.println(e.getMessage());
            System.out.println("-----------------------------------STACK TRACE END------------------------------------------");
            System.out.println("ERROR MESSAGE : purchaseBond : BankAssets : threw an exception");
        }

        return savedLiabilities;
    }

    public void bankAssetReduction(BankAssets bankAsset ,double amount) throws Exception
    {
        if(amount > bankAsset.getCashValue())
        {
            throw new InsufficientFundsException("Insufficient BankAssets : Bank is Insolvent.");
        }
        else{
            bankAsset.setCashValue(bankAsset.getCashValue() - amount);
            bankAsset.setLastUpdate(LocalDate.now());
            bankAssetsRepository.save(bankAsset);
        }
    }

    public boolean sellBond(int liabilityId)
    {
        try{
            Liabilities bond = liabilitiesRepository.findById(liabilityId).get();
            BankAssets bankAssets = bankAssetsRepository.getReferenceById(BANK_ASSET_ID);
            bankAssetReduction(bankAssets,bond.getLiabilityValue());
            liabilitiesRepository.deleteById(liabilityId);
        }catch(Exception e){
            System.out.println("-----------------------------------STACK TRACE START----------------------------------------");
            System.out.println(e.getMessage());
            System.out.println("-----------------------------------STACK TRACE END------------------------------------------");
            System.out.println("ERROR MESSAGE : Bond : BankAssets : threw an exception");
            return false;
        }
        return true;
    }

    public boolean cashDeposit(int liabilityId , double amount) {
        boolean result = true;
        Liabilities liabilities;
        try{
            liabilities = liabilitiesRepository.getReferenceById(liabilityId);
            liabilities.setLiabilityValue(liabilities.getLiabilityValue() + amount);
            liabilitiesRepository.save(liabilities);
        }catch (Exception e){
            System.out.println("-----------------------------------STACK TRACE START----------------------------------------");
            System.out.println(e.getMessage());
            System.out.println("-----------------------------------STACK TRACE END------------------------------------------");
            System.out.println("ERROR MESSAGE : cashDeposit : liabilityRepository threw an exception");
            result = false;
        }

        try{
            BankAssets bankAssets = bankAssetsRepository.getReferenceById(BANK_ASSET_ID);
            bankAssets.setCashValue(bankAssets.getCashValue() + amount);
            bankAssets.setLastUpdate(LocalDate.now());
            bankAssetsRepository.save(bankAssets);
        }catch (Exception e){
            System.out.println("-----------------------------------STACK TRACE START----------------------------------------");
            System.out.println(e.getMessage());
            System.out.println("-----------------------------------STACK TRACE END------------------------------------------");
            System.out.println("ERROR MESSAGE : cashDeposit : bankAssetsRepository threw an exception");
            result = false;
        }
        return result;
    }

    public String cashWithdrawal(int liabilityId , double amount) {
        Liabilities liabilities;
        BankAssets bankAssets = bankAssetsRepository.getReferenceById(BANK_ASSET_ID);
        try{
            liabilities = liabilitiesRepository.getReferenceById(liabilityId);
            if(liabilities.getLiabilityValue() < amount)
            {
                return "InsufficientFunds";
            }
            else if(bankAssets.getCashValue() < amount){
                return "BankInsolvent";
            }
            else{
                System.out.println("Liability Value : " + liabilities.getLiabilityValue());
                liabilities.setLiabilityValue(liabilities.getLiabilityValue() - amount);
                liabilitiesRepository.save(liabilities);

                System.out.println("Banks Assets Value : " +  bankAssets.getCashValue());
                bankAssetReduction(bankAssets,amount);
                return "Success";
            }
        }catch (Exception e){
            System.out.println("-----------------------------------STACK TRACE START----------------------------------------");
            System.out.println(e.getMessage());
            System.out.println("-----------------------------------STACK TRACE END------------------------------------------");
            System.out.println("ERROR MESSAGE : cashWithdrawal threw an exception");
            return "Internal Error";
        }
    }



}
