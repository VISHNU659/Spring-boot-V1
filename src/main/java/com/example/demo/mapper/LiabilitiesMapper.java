package com.example.demo.mapper;

import com.example.demo.DTO.BondCreationDto;
import com.example.demo.DTO.LiabilitiesCreationDto;
import com.example.demo.entity.Liabilities;

import java.time.LocalDate;

public class LiabilitiesMapper {

    public static Liabilities toLiabilities(LiabilitiesCreationDto dto) {
        Liabilities liabilities = new Liabilities();

        liabilities.setCustomerId(dto.getCustomerId());
        liabilities.setLiabilityType(dto.getLiabilityType());
        liabilities.setAnnualInterestRate(dto.getAnnualInterestRate());
        liabilities.setInterestPayBackDate(dto.getInterestPayBackDate());
        liabilities.setCreationDate(LocalDate.now());
        return liabilities;
    }

    public static Liabilities toLiabilities(BondCreationDto dto) {
        Liabilities liabilities = new Liabilities();

        liabilities.setCustomerId(dto.getCustomerId());
        liabilities.setLiabilityType(dto.getLiabilityType());
        liabilities.setLiabilityValue(dto.getLiabilityValue());
        liabilities.setAnnualInterestRate(dto.getAnnualInterestRate());
        liabilities.setInterestPayBackDate(dto.getInterestPayBackDate());
        liabilities.setMaturityDate(dto.getMaturityDate());
        liabilities.setCreationDate(LocalDate.now());
        return liabilities;
    }
}
