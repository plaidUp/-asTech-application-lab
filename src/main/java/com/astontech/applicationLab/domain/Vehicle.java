package com.astontech.applicationLab.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Vehicle {

    //region PROPERTIES
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "VehicleId")
    private Integer vehicleId;

    @Version
    private Integer version;

    private String vehicleName;
    private String licensePlate;
    private String vin;
    private int year;
    private String color;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "VehicleModelId")
    private VehicleModel vehicleModel;

    //endregion

    //region CONSTRUCTORS
    public Vehicle() {}

    public Vehicle(String licensePlate, String vin, int year, String color, VehicleModel vehicleModel) {
        this.licensePlate = licensePlate;
        this.vin = vin;
        this.year = year;
        this.color = color;
        this.vehicleModel = vehicleModel;
    }

    //endregion



}
