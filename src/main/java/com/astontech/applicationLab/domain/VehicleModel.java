package com.astontech.applicationLab.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class VehicleModel {

    //region PROPERTIES
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "VehicleModelId")
    private Integer vehicleModelId;

    @Version
    private Integer version;

    private String vehicleModelName;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "VehicleMakeId")
    private VehicleMake vehicleMake;


    //endregion

    //region CONSTRUCTORS

    public VehicleModel() {}
    public VehicleModel(String vehicleModelName) {
        this.setVehicleModelName(vehicleModelName);
    }
    public VehicleModel(String vehicleModelName, VehicleMake vehicleMake) {
        this.setVehicleModelName(vehicleModelName);
        this.setVehicleMake(vehicleMake);
    }

    //endregion
}
