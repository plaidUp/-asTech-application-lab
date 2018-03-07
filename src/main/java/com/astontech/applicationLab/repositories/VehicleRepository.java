package com.astontech.applicationLab.repositories;


import com.astontech.applicationLab.domain.Vehicle;
import com.astontech.applicationLab.domain.VehicleModel;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface VehicleRepository extends CrudRepository<Vehicle, Integer> {

    Vehicle findByVehicleName(String vehicleName);

    List<Vehicle> findByVehicleModel(VehicleModel vehicleModel);

    Vehicle findByVin(String vin);

    List<Vehicle> findAll();

    void deleteAllByVehicleModel(VehicleModel vehicleModel);


}
