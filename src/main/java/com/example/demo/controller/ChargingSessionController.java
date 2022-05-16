package com.example.demo.controller;

import com.example.demo.model.ChargingSession;
import com.example.demo.service.ChargingSessionService;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping(path = "session")
public class ChargingSessionController {

    private final ChargingSessionService chargingSessionService;

    public ChargingSessionController(ChargingSessionService chargingSessionService) {
        this.chargingSessionService = chargingSessionService;
    }

    @PostMapping(path = "start")
    public Long startNewChargingSession(
            @RequestParam(name = "rfid_id") Long rfidId,
            @RequestParam(name = "charge_point_id") Long chargePointId,
            @RequestParam(name = "time") String startTimeStr,
            @RequestParam(name = "meter_value") BigDecimal startValue
            ){

        return chargingSessionService.startAChargingSession(
                rfidId, chargePointId, startTimeStr, startValue);

    }

    @PutMapping(path = "terminate/{id}")
    public void terminateChargingSession(@PathVariable Long id,
                                         @RequestParam(name = "time") String endTimeStr,
                                         @RequestParam(name = "meter_value") BigDecimal endValue){

        chargingSessionService.terminateChargingSession(id, endTimeStr, endValue);
    }

    @GetMapping
    public List<ChargingSession> getChargingSessionByDateTimeRange(
            @RequestParam(name = "start_time") String startTimeStr,
            @RequestParam(name = "end_time") String endTimeStr) {

        return chargingSessionService.selectSessionsByTimeRange(startTimeStr, endTimeStr);
    }
}
