package com.example.demo.mapper;

import com.example.demo.DTO.LiabilitiesDto;
import com.example.demo.entity.Liabilities;

public class LiabilitiesMapper {

    public static Liabilities toLiabilities(LiabilitiesDto dto) {
        Liabilities liabilities = new Liabilities();

        liabilities.setLiabilityId(dto.getLiabilityId());
        liabilities.setCustomerId(dto.getCustomerId());
        liabilities.setStartDate(dto.getStartDate());
        liabilities.setInterestRate(dto.getInterestRate());
        liabilities.setDepositPrincipalAmount(dto.getDepositPrincipalAmount());

        return liabilities;
    }

    public static LiabilitiesDto toLiabilitiesDto(Liabilities liabilities) {
        LiabilitiesDto liabilitiesDto = new LiabilitiesDto();

        liabilitiesDto.setLiabilityId(liabilities.getLiabilityId());
        liabilitiesDto.setCustomerId(liabilities.getCustomerId());
        liabilitiesDto.setStartDate(liabilities.getStartDate());
        liabilitiesDto.setInterestRate(liabilities.getInterestRate());
        liabilitiesDto.setDepositPrincipalAmount(liabilities.getDepositPrincipalAmount());
        return liabilitiesDto;
    }
}
