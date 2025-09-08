package com.example.demo.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;

public class LiabilitiesCreationDto {
    @JsonProperty("customerId")
    private int customerId;
    @JsonProperty("liabilityType")
    private String liabilityType;
    @JsonProperty("annualInterestRate")
    private double annualInterestRate;
    @JsonProperty("interestPayBackDate")
    private LocalDate interestPayBackDate;
    @JsonProperty("maturityDate")
    private LocalDate maturityDate;

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getLiabilityType() {
        return liabilityType;
    }

    public void setLiabilityType(String liabilityType) {
        this.liabilityType = liabilityType;
    }

    public double getAnnualInterestRate() {
        return annualInterestRate;
    }

    public void setAnnualInterestRate(double annualInterestRate) {
        this.annualInterestRate = annualInterestRate;
    }

    public LocalDate getInterestPayBackDate() {
        return interestPayBackDate;
    }

    public void setInterestPayBackDate(LocalDate interestPayBackDate) {
        this.interestPayBackDate = interestPayBackDate;
    }

    public LocalDate getMaturityDate() {
        return maturityDate;
    }

    public void setMaturityDate(LocalDate maturityDate) {
        this.maturityDate = maturityDate;
    }
}
