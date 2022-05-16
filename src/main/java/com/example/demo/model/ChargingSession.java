package com.example.demo.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity(name = "ChargingSession")
@Table(name = "charging_session")
public class ChargingSession {
    @Id
    @SequenceGenerator(name = "charging_session_sequence",
            sequenceName = "charging_session_sequence",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "charging_session_sequence")
    @Column(name = "id", updatable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "rfid_id",
            referencedColumnName = "id",
            nullable = false,
            foreignKey = @ForeignKey(name = "session_rfid_id_fk"))
    private Rfid rfid;

    @ManyToOne
    @JoinColumn(name = "charge_point_id",
            referencedColumnName = "id",
            nullable = false,
            foreignKey = @ForeignKey(name = "session_charge_point_id_fk"))
    private ChargePoint chargePoint;

    @Column(
            name = "start_time",
            nullable = false,
            columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private LocalDateTime startTime;

    @Column(
            name = "start_value",
            nullable = false,
            precision = 10,
            scale = 2)
    private BigDecimal startValue;

    @Column(
            name = "end_time",
            columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private LocalDateTime endTime;

    @Column(
            name = "end_value",
            precision = 10,
            scale = 2)
    private BigDecimal endValue;

    @Column(
            name = "error_message",
            columnDefinition = "TEXT")
    private String errorMessage;

    public ChargingSession() {
    }

    public ChargingSession(Rfid rfid, ChargePoint chargePoint, LocalDateTime startTime, BigDecimal startValue) {
        this.rfid = rfid;
        this.chargePoint = chargePoint;
        this.startTime = startTime;
        this.startValue = startValue;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Rfid getRfid() {
        return rfid;
    }

    public void setRfid(Rfid rfid) {
        this.rfid = rfid;
    }

    public ChargePoint getChargePoint() {
        return chargePoint;
    }

    public void setChargePoint(ChargePoint chargePoint) {
        this.chargePoint = chargePoint;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public BigDecimal getStartValue() {
        return startValue;
    }

    public void setStartValue(BigDecimal startValue) {
        this.startValue = startValue;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public BigDecimal getEndValue() {
        return endValue;
    }

    public void setEndValue(BigDecimal endValue) {
        this.endValue = endValue;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public void endCharging(LocalDateTime endTime, BigDecimal endValue){
        this.endTime = endTime;
        this.endValue = endValue;
    }

    @Override
    public String toString() {
        return "ChargingSession{" +
                "id=" + id +
                ", rfid=" + rfid +
                ", chargePoint=" + chargePoint +
                ", startTime=" + startTime +
                ", startValue=" + startValue +
                ", endTime=" + endTime +
                ", endValue=" + endValue +
                ", errorMessage='" + errorMessage + '\'' +
                '}';
    }
}
