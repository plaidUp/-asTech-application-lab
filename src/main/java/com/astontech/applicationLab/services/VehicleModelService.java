package com.astontech.applicationLab.services;

import com.astontech.applicationLab.domain.VehicleMake;
import com.astontech.applicationLab.domain.VehicleModel;

import java.util.List;

public interface VehicleModelService {

    List<VehicleModel> listAllVehicleModels();

    VehicleModel getVehicleModelById(Integer id);

    VehicleModel saveVehicleModel(VehicleModel vehicleModel);

    Iterable<VehicleModel> saveVehicleModel(Iterable<VehicleModel> vehicleModelIterable);

    void deleteVehicleModel(Integer id);

    VehicleModel findByVehicleModelName(String vehicleModelName);

    VehicleModel getVehicleModelByName(String vehicleModelName);

    Iterable<VehicleModel> listAllVehiclesByModelId(int vehicleModelId);

    List<VehicleModel> getAllVehicleModelsByMake(VehicleMake vehicleMake);

    List<VehicleModel> listAllVehicleModelsByName(String vehicleModelName);

    List<VehicleModel> listAllVehicleModelsByMake(VehicleMake vehicleMake);
}
