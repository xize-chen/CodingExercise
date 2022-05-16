package com.example.demo.repository;

import com.example.demo.model.ChargePoint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChargePointRepository extends JpaRepository<ChargePoint, Long> {
}
