package com.example.demo.mapper;

import com.example.demo.DTO.TransactionDto;
import com.example.demo.entity.Transaction;

public class TransactionMapper {

    public static TransactionDto ToTransactionDto(Transaction entity) {
        TransactionDto transactionDto = new TransactionDto();

        transactionDto.setTransactionId(entity.getTransactionId());
        transactionDto.setTransactionDate(entity.getTransactionDate());
        transactionDto.setTransactionType(entity.getTransactionType());
        transactionDto.setTransactionAmount(entity.getTransactionAmount());
        transactionDto.setCustomerId(entity.getCustomerId());

        return transactionDto;
    }

    public static Transaction ToEntity(TransactionDto transactionDto) {
        Transaction entity = new Transaction();
        entity.setTransactionId(transactionDto.getTransactionId());
        entity.setTransactionDate(transactionDto.getTransactionDate());
        entity.setTransactionType(transactionDto.getTransactionType());
        entity.setTransactionAmount(transactionDto.getTransactionAmount());
        entity.setCustomerId(transactionDto.getCustomerId());
        return entity;
    }
}
