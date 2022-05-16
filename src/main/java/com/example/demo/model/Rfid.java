package com.example.demo.model;

import javax.persistence.*;

@Entity(name = "Rfid")
@Table(name = "rfid", uniqueConstraints = {
        @UniqueConstraint(name = "rfid_unique", columnNames = "serial_number") })
public class Rfid {
    @Id
    @SequenceGenerator(name = "rfid_sequence",
            sequenceName = "rfid_sequence",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "rfid_sequence")
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "serial_number", nullable = false)
    private String serialNumber;

    @ManyToOne
    @JoinColumn( name = "customer_id",
            referencedColumnName = "id",
            nullable = false,
            foreignKey = @ForeignKey( name = "rfid_customer_fk"))
    private Customer customer;

    //cascade = CascadeType.ALL
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(
            name = "vehicle_id",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "rfid_vehicle_fk")
    )
    private Vehicle vehicle;

    public Rfid() {
    }

    public Rfid(String serialNumber, Customer customer) {
        this.serialNumber = serialNumber;
        this.customer = customer;
    }

    public Rfid(String serialNumber, Customer customer, Vehicle vehicle) {
        this.serialNumber = serialNumber;
        this.customer = customer;
        this.vehicle = vehicle;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    @Override
    public String toString() {
        return "Rfid{" +
                "id=" + id +
                ", serialNumber='" + serialNumber + '\'' +
                ", customer=" + customer +
                ", vehicle=" + vehicle +
                '}';
    }
}
