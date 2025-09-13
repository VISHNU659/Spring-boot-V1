package com.example.demo.repository;

import com.example.demo.entity.Liabilities;
import com.example.demo.enums.LiabilityType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LiabilitiesRepository extends JpaRepository<Liabilities,Integer> {

    @Query(value = "SELECT a from Liabilities a WHERE a.customerId = :customerId and a.liabilityType = :liabilityType")
    List<Liabilities> findByCustomerIdAndLiabilityType(int customerId, LiabilityType liabilityType);

    
}
