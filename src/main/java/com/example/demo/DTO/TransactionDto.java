package com.example.demo.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;

public class TransactionDto {
    @JsonProperty("transactionId")
    private int transactionId;
    @JsonProperty("customerId")
    private int customerId;
    @JsonProperty("transactionType")
    private String transactionType;
    @JsonProperty("transactionAmount")
    private double transactionAmount;
    @JsonProperty("transactionDate")
    private LocalDate transactionDate;

    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public double getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(double transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public LocalDate getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(LocalDate transactionDate) {
        this.transactionDate = transactionDate;
    }
}


