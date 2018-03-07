package com.astontech.applicationLab.repositories;

import com.astontech.applicationLab.domain.VehicleMake;
import com.astontech.applicationLab.domain.VehicleModel;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface VehicleModelRepository extends CrudRepository<VehicleModel, Integer> {

    VehicleModel findByVehicleModelName(String vehicleModelName);

    Iterable<VehicleModel> findAllByVehicleModelId(int vehicleModelId);

    List<VehicleModel> findAllByVehicleMake(VehicleMake vehicleMake);

    List<VehicleModel> findAll();

    List<VehicleModel> findAllByVehicleModelName(String vehicleModelName);
}
