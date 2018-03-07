package com.astontech.applicationLab.services;

import com.astontech.applicationLab.domain.Vehicle;
import com.astontech.applicationLab.domain.VehicleModel;

import java.util.List;

public interface VehicleService {

    List<Vehicle> listAllVehicles();

    List<Vehicle> listAllVehiclesByModel(VehicleModel vehicleModel);

    Vehicle getVehicleById(Integer id);

    Vehicle saveVehicle(Vehicle vehicle);

    Iterable<Vehicle> saveVehicle(Iterable<Vehicle> vehicleIterable);

    void deleteVehicle(Integer id);

    Vehicle findByVehicleName(String vehicleName);

    Vehicle findByVehicle(String vin);

    void deleteAllVehicles(VehicleModel vehicleModel);



}
