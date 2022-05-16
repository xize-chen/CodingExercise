package com.example.demo.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "Customer")
@Table(name = "customer")
public class Customer {
    @Id
    @SequenceGenerator(name = "customer_sequence",
                       sequenceName = "customer_sequence",
                       allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
                    generator = "customer_sequence")
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "name",nullable = false)
    private String name;

    //fetch = FetchType.LAZY
    @OneToMany(
            mappedBy = "customer",
            orphanRemoval = true,
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE}
    )
    private List<ChargePoint> chargePoints = new ArrayList<>();

    @OneToMany(
            mappedBy = "customer",
            orphanRemoval = true,
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE}
    )
    private List<Rfid> rfids = new ArrayList<>();

    public Customer() {
    }

    public Customer(String name) {
        this.name = name;
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

    public void addRfid(Rfid rfid) {
        if (!this.rfids.contains(rfid)) {
            this.rfids.add(rfid);
            rfid.setCustomer(this);
        }
    }
    public void addChargePoint(ChargePoint cp) {
        if (!this.chargePoints.contains(cp)) {
            this.chargePoints.add(cp);
            cp.setCustomer(this);
        }
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
