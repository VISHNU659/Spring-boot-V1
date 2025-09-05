package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDate;

@Entity
@Table(name = "Liabilities")
public class Liabilities {
    @Id
    private int liabilityId;
    private int customerId;
    private double depositPrincipalAmount;
    private double interestRate;
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
