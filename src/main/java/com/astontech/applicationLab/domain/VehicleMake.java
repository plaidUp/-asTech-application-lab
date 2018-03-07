package com.astontech.applicationLab.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class VehicleMake {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "VehicleMakeId")
    private Integer vehicleMakeId;

    @Version
    private Integer version;

    private String vehicleMakeName;



    public VehicleMake() {}
    public VehicleMake(String vehicleMakeName) {
        this.vehicleMakeName = vehicleMakeName;
    }
}
