package com.example.demo.model;

import javax.persistence.*;

@Entity(name = "Connector")
@Table(name = "connector")
public class Connector {
    @Id
    @SequenceGenerator(name = "connector_sequence",
            sequenceName = "connector_sequence",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "connector_sequence")
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "connector_number",nullable = false)
    private String connectorNumber;

    @ManyToOne
    @JoinColumn(
            name = "charge_point_id",
            referencedColumnName = "id",
            nullable = false,
            foreignKey = @ForeignKey(name = "connector_charge_point_fk")
    )
    private ChargePoint chargePoint;

    public Connector() {
    }

    public Connector(String connectorNumber, ChargePoint chargePoint) {
        this.connectorNumber = connectorNumber;
        this.chargePoint = chargePoint;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getConnectorNumber() {
        return connectorNumber;
    }

    public void setConnectorNumber(String connectorNumber) {
        this.connectorNumber = connectorNumber;
    }

    public ChargePoint getChargePoint() {
        return chargePoint;
    }

    public void setChargePoint(ChargePoint chargePoint) {
        this.chargePoint = chargePoint;
    }

    @Override
    public String toString() {
        return "Connector{" +
                "id=" + id +
                ", connectorNumber='" + connectorNumber + '\'' +
                ", chargePoint=" + chargePoint +
                '}';
    }
}
