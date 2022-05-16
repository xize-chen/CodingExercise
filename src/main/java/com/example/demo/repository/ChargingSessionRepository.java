package com.example.demo.repository;

import com.example.demo.model.ChargingSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ChargingSessionRepository extends JpaRepository<ChargingSession, Long> {

    @Transactional
    @Modifying
    @Query("UPDATE ChargingSession session " +
            "SET session.endTime = :dateTime, session.endValue =:endValue " +
            "WHERE session.id = :id")
    void updateEndPointById(
            @Param("id") Long id,
            @Param("dateTime") LocalDateTime dateTime,
            @Param("endValue") BigDecimal endValue);

    List<ChargingSession> findChargingSessionsByStartTimeBetween(LocalDateTime start, LocalDateTime end);
}
