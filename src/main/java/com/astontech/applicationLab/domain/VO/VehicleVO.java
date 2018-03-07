package com.astontech.applicationLab.domain.VO;

import com.astontech.applicationLab.domain.Vehicle;
import com.astontech.applicationLab.domain.VehicleMake;
import com.astontech.applicationLab.domain.VehicleModel;
import lombok.Data;

import java.util.List;

@Data
public class VehicleVO {


    private String newVehicle;
    private String newVehicleMake;
    private String[] newVehicleMakeArray;
    private String newVehicleModel;
    private String[] newVehicleModelArray;
    private String newLicensePlate;
    private String newVin;
    private String newYear;
    private String newColor;
    private List<VehicleVO> vehicleVOList;

    private Vehicle vehicle;
    private VehicleModel vehicleModel;
    private VehicleMake vehicleMake;

    private List<VehicleModel> vehicleModelList;
    private List<Vehicle> vehicleList;

    public String getNewVehicle() {
        return newVehicle;
    }

    public void splitNewElementsIntoArray() {
        this.newVehicleModelArray = this.newVehicleModel.split("\\r?\\n");
    }
}
