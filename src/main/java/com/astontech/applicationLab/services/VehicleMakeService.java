package com.astontech.applicationLab.services;

import com.astontech.applicationLab.domain.VehicleMake;

import java.util.List;

public interface VehicleMakeService {

    List<VehicleMake> listAllVehicleMakes();

    VehicleMake getVehicleMakeById(Integer id);

    VehicleMake saveVehicleMake(VehicleMake vehicleMake);

    Iterable<VehicleMake> saveVehicleMake(Iterable<VehicleMake> vehicleMakeIterable);

    void deleteVehicleMake(Integer id);

    public VehicleMake findByVehicleMakeName(String vehicleMakeName);

}
