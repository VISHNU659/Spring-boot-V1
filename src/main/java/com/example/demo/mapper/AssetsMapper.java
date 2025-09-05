package com.example.demo.mapper;

import com.example.demo.DTO.AssetsDto;
import com.example.demo.entity.Assets;

public class AssetsMapper {

    public static AssetsDto toDto(Assets entity) {
        AssetsDto dto = new AssetsDto();

        dto.setAssetId(entity.getAssetId());
        dto.setCustomerId(entity.getCustomerId());
        dto.setInterestRate(entity.getInterestRate());
        dto.setStartDate(entity.getStartDate());
        dto.setMaturityDate(entity.getMaturityDate());
        dto.setLoanPrincipalAmount(entity.getLoanPrincipalAmount());

        return dto;
    }

    public static Assets toEntity(AssetsDto dto) {
        Assets entity = new Assets();

        entity.setAssetId(dto.getAssetId());
        entity.setCustomerId(dto.getCustomerId());
        entity.setInterestRate(dto.getInterestRate());
        entity.setStartDate(dto.getStartDate());
        entity.setMaturityDate(dto.getMaturityDate());
        entity.setLoanPrincipalAmount(dto.getLoanPrincipalAmount());
        return entity;
    }
}
