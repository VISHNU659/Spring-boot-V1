package com.example.demo.mapper;

import com.example.demo.DTO.LiabilitiesCreationDto;
import com.example.demo.DTO.LiabilitiesDto;
import com.example.demo.entity.Liabilities;

public class LiabilitiesMapper {

    public static Liabilities toLiabilities(LiabilitiesDto dto) {
        Liabilities liabilities = new Liabilities();

        liabilities.setLiabilityId(dto.getLiabilityId());
        liabilities.setCustomerId(dto.getCustomerId());
        liabilities.setLiabilityType(dto.getLiabilityType());
        liabilities.setLiabilityValue(dto.getLiabilityValue());
        liabilities.setAnnualInterestRate(dto.getAnnualInterestRate());
        liabilities.setInterestPayBackDate(dto.getInterestPayBackDate());
        liabilities.setMaturityDate(dto.getMaturityDate());
        return liabilities;
    }

    public static Liabilities toLiabilities(LiabilitiesCreationDto dto) {
        Liabilities liabilities = new Liabilities();

        liabilities.setCustomerId(dto.getCustomerId());
        liabilities.setLiabilityType(dto.getLiabilityType());
        liabilities.setAnnualInterestRate(dto.getAnnualInterestRate());
        liabilities.setInterestPayBackDate(dto.getInterestPayBackDate());
        liabilities.setMaturityDate(dto.getMaturityDate());
        return liabilities;
    }

    public static LiabilitiesDto toLiabilitiesDto(Liabilities liabilities) {
        LiabilitiesDto liabilitiesDto = new LiabilitiesDto();

        liabilitiesDto.setLiabilityId(liabilities.getLiabilityId());
        liabilitiesDto.setCustomerId(liabilities.getCustomerId());
        liabilitiesDto.setLiabilityType(liabilities.getLiabilityType().toString());
        liabilitiesDto.setLiabilityValue(liabilities.getLiabilityValue());
        liabilitiesDto.setAnnualInterestRate(liabilities.getAnnualInterestRate());
        liabilitiesDto.setInterestPayBackDate(liabilities.getInterestPayBackDate());
        liabilitiesDto.setMaturityDate(liabilities.getMaturityDate());
        return liabilitiesDto;
    }
}
