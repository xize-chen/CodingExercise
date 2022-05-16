package com.example.demo.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "ChargePoint")
@Table(name = "charge_point", uniqueConstraints = {
        @UniqueConstraint(name = "charge_point_serial_unique", columnNames = "serial_number") })
public class ChargePoint {

    @Id
    @SequenceGenerator(name = "charge_point_sequence",
            sequenceName = "charge_point_sequence",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "charge_point_sequence")
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "name",nullable = false)
    private String name;

    @Column(name = "serial_number",nullable = false)
    private String serialNumber;

    @ManyToOne
    @JoinColumn( name = "customer_id",
            referencedColumnName = "id",
            nullable = false,
            foreignKey = @ForeignKey( name = "charge_point_customer_fk"))
    private Customer customer;

    @OneToMany(mappedBy = "chargePoint",
            orphanRemoval = true,
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private List<Connector> connectors = new ArrayList<>();

    public ChargePoint() {
    }

    public ChargePoint(String name, String serialNumber, Customer customer) {
        this.name = name;
        this.serialNumber = serialNumber;
        this.customer = customer;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void addConnector(Connector connector) {
        if (!this.connectors.contains(connector)) {
            this.connectors.add(connector);
            connector.setChargePoint(this);
        }
    }
    public void removeConnector(Connector connector) {
        if (this.connectors.contains(connector)) {
            this.connectors.remove(connector);
            connector.setChargePoint(null);
        }
    }

    @Override
    public String toString() {
        return "ChargePoint{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", serialNumber='" + serialNumber + '\'' +
                ", customer=" + customer +
                '}';
    }
}