package com.astontech.applicationLab.services.impl;

import com.astontech.applicationLab.domain.Vehicle;
import com.astontech.applicationLab.domain.VehicleModel;
import com.astontech.applicationLab.repositories.VehicleRepository;
import com.astontech.applicationLab.services.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleServiceImpl implements VehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;

    @Override
    public List<Vehicle> listAllVehicles() {
        return vehicleRepository.findAll();
    }

    @Override
    public Vehicle getVehicleById(Integer id) {
        return vehicleRepository.findOne(id);
    }

    @Override
    public Vehicle saveVehicle(Vehicle vehicle) {
        return vehicleRepository.save(vehicle);
    }

    @Override
    public Iterable<Vehicle> saveVehicle(Iterable<Vehicle> vehicleIterable) {
        return vehicleRepository.save(vehicleIterable);
    }

    @Override
    public void deleteVehicle(Integer id) {
        vehicleRepository.delete(id);
    }

    @Override
    public Vehicle findByVehicleName(String vehicleName) {
        return vehicleRepository.findByVehicleName(vehicleName);
    }

    @Override
    public List<Vehicle> listAllVehiclesByModel(VehicleModel vehicleModel) {
        return vehicleRepository.findByVehicleModel(vehicleModel);
    }

    @Override
    public Vehicle findByVehicle(String vin) {
        return vehicleRepository.findByVin(vin);
    }

    @Override
    public void deleteAllVehicles(VehicleModel vehicleModel) {
        vehicleRepository.deleteAllByVehicleModel(vehicleModel);
    }
}
