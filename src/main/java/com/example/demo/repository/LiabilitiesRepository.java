package com.example.demo.repository;

import com.example.demo.entity.Liabilities;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LiabilitiesRepository extends JpaRepository<Liabilities,Integer> {
}
