package com.example.demo.entity;

import com.example.demo.enums.LiabilityType;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "Liabilities")
public class Liabilities {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int liabilityId;
    private int customerId;
    @Enumerated(EnumType.STRING)
    private LiabilityType liabilityType;
    private double LiabilityValue;
    private double annualInterestRate;
    private LocalDate interestPayBackDate;
    @Column(nullable = true)
    private LocalDate maturityDate;

    public LiabilityType getLiabilityType() {
        return liabilityType;
    }

    public void setLiabilityType(String liabilityType) {
        this.liabilityType = LiabilityType.valueOf(liabilityType);
    }

    public double getLiabilityValue() {
        return LiabilityValue;
    }

    public void setLiabilityValue(double liabilityValue) {
        LiabilityValue = liabilityValue;
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
}
