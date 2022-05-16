package com.example.demo.model;

import javax.persistence.*;

@Entity(name = "Vehicle")
@Table(name = "vehicle", uniqueConstraints = {
        @UniqueConstraint(name = "vehicle_unique", columnNames = "plate_number") })
public class Vehicle {

    @Id
    @SequenceGenerator(name = "vehicle_sequence",
            sequenceName = "vehicle_sequence",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "vehicle_sequence")
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "plate_number", nullable = false)
    private String plateNumber;


    public Vehicle() {
    }

    public Vehicle(String name, String plateNumber) {
        this.name = name;
        this.plateNumber = plateNumber;
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

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }


    @Override
    public String toString() {
        return "Vehicle{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", plateNumber='" + plateNumber + '\'' +
                '}';
    }
}
