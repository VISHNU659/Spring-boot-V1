package com.example.demo.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public class AssetsDto {
    @NotNull
    @JsonProperty("assetId")
    private int assetId;
    @JsonProperty("customerId")
    private int customerId;
    @JsonProperty("loanPrincipalAmount")
    private double loanPrincipalAmount;
    @JsonProperty("interestRate")
    private double interestRate;
    @JsonProperty("startDate")
    private LocalDate startDate;
    @JsonProperty("maturityDate")
    private LocalDate maturityDate;

    public int getAssetId() {
        return assetId;
    }

    public void setAssetId(int assetId) {
        this.assetId = assetId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public double getLoanPrincipalAmount() {
        return loanPrincipalAmount;
    }

    public void setLoanPrincipalAmount(double loanPrincipalAmount) {
        this.loanPrincipalAmount = loanPrincipalAmount;
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

    public LocalDate getMaturityDate() {
        return maturityDate;
    }

    public void setMaturityDate(LocalDate maturityDate) {
        this.maturityDate = maturityDate;
    }
}
