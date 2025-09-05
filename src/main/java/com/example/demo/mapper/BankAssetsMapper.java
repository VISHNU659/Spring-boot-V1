package com.example.demo.mapper;

import com.example.demo.DTO.BankAssetsDto;
import com.example.demo.entity.BankAssets;

public class BankAssetsMapper {

    public static BankAssets toEntity(BankAssetsDto dto)
    {
        BankAssets entity = new BankAssets();

        entity.setAssetId(dto.getAssetId());
        entity.setCashValue(dto.getCashValue());
        entity.setLastUpdate(dto.getLastUpdate());

        return entity;
    }

    public static BankAssetsDto toDto(BankAssets entity)
    {
        BankAssetsDto dto = new BankAssetsDto();
        dto.setAssetId(entity.getAssetId());
        dto.setCashValue(entity.getCashValue());
        dto.setLastUpdate(entity.getLastUpdate());

        return dto;
    }
}
