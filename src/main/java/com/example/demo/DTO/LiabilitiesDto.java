package com.example.demo.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;

public class LiabilitiesDto {
    @JsonProperty("liabilityId")
    private int liabilityId;
    @JsonProperty("customerId")
    private int customerId;
    @JsonProperty("depositPrincipalAmount")
    private double depositPrincipalAmount;
    @JsonProperty("interestRate")
    private double interestRate;
    @JsonProperty("startDate")
    private LocalDate startDate;

    public int getLiabilityId() {
        return liabilityId;
    }

    public void setLiabilityId(int liabilityId) {
        this.liabilityId = liabilityId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public double getDepositPrincipalAmount() {
        return depositPrincipalAmount;
    }

    public void setDepositPrincipalAmount(double depositPrincipalAmount) {
        this.depositPrincipalAmount = depositPrincipalAmount;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }
}
