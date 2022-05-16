package com.example.demo.service;

import com.example.demo.model.ChargePoint;
import com.example.demo.model.ChargingSession;
import com.example.demo.model.Rfid;
import com.example.demo.repository.ChargePointRepository;
import com.example.demo.repository.ChargingSessionRepository;
import com.example.demo.repository.RfidRepository;
import com.example.demo.utility.DateTimeUtil;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ChargingSessionService {
    private final RfidRepository rfidRepository;
    private final ChargePointRepository chargePointRepository;
    private final ChargingSessionRepository chargingSessionRepository;

    public ChargingSessionService(RfidRepository rfidRepository,
                                  ChargePointRepository chargePointRepository,
                                  ChargingSessionRepository chargingSessionRepository) {
        this.rfidRepository = rfidRepository;
        this.chargePointRepository = chargePointRepository;
        this.chargingSessionRepository = chargingSessionRepository;
    }

    public Long startAChargingSession(Long rfidId, Long chargePointId, String timeStr, BigDecimal startValue){
        Rfid rfid = rfidRepository.findById(rfidId)
                .orElseThrow(() ->
                        new IllegalStateException(String.format("RFID with id: %s is not found", rfidId))
                );
        ChargePoint chargePoint = chargePointRepository.findById(chargePointId).orElseThrow(() ->
                new IllegalStateException(String.format("Charge point with id: %s is not found", chargePointId))
                );
        LocalDateTime startTime = getDateTime(timeStr);
        if(rfid.getId().compareTo(chargePoint.getId()) != 0){
            throw new IllegalStateException("RFID not authorized");
        }
        ChargingSession session = chargingSessionRepository.save(new ChargingSession(rfid,chargePoint,startTime,startValue));
        return session.getId();
    }

    public void terminateChargingSession(Long sessionId, String timeStr, BigDecimal endValue){
        LocalDateTime endTime = getDateTime(timeStr);
        chargingSessionRepository.updateEndPointById(sessionId, endTime, endValue);
    }

    public List<ChargingSession> selectSessionsByTimeRange(String start, String end){
        return chargingSessionRepository.findChargingSessionsByStartTimeBetween(
                getDateTime(start),
                getDateTime(end)
        );
    }

    private LocalDateTime getDateTime(String timeStr){
        return DateTimeUtil.convert(timeStr).orElseThrow(() ->
                new IllegalStateException("Invalid start time format -> " + timeStr)
        );
    }


}
