package com.astontech.applicationLab.services.impl;

import com.astontech.applicationLab.domain.VehicleMake;
import com.astontech.applicationLab.domain.VehicleModel;
import com.astontech.applicationLab.repositories.VehicleModelRepository;
import com.astontech.applicationLab.services.VehicleModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleModelServiceImpl implements VehicleModelService{

    @Autowired
    private VehicleModelRepository vehicleModelRepository;

    @Override
    public List<VehicleModel> listAllVehicleModels() {
        return vehicleModelRepository.findAll();
    }

    @Override
    public VehicleModel getVehicleModelById(Integer id) {
        return vehicleModelRepository.findOne(id);
    }

    @Override
    public VehicleModel saveVehicleModel(VehicleModel vehicleModel) {
        return vehicleModelRepository.save(vehicleModel);
    }

    @Override
    public Iterable<VehicleModel> saveVehicleModel(Iterable<VehicleModel> vehicleModelIterable) {
        return vehicleModelRepository.save(vehicleModelIterable);
    }

    @Override
    public void deleteVehicleModel(Integer id) {
        vehicleModelRepository.delete(id);
    }

    @Override
    public VehicleModel findByVehicleModelName(String vehicleModelName) {
        return vehicleModelRepository.findByVehicleModelName(vehicleModelName);
    }

    @Override
    public VehicleModel getVehicleModelByName(String vehicleModelName) {
        return vehicleModelRepository.findByVehicleModelName(vehicleModelName);
    }

    @Override
    public Iterable<VehicleModel> listAllVehiclesByModelId(int vehicleModelId){
        return vehicleModelRepository.findAllByVehicleModelId(vehicleModelId);
    }

    @Override
    public List<VehicleModel> getAllVehicleModelsByMake(VehicleMake vehicleMake) {
        return vehicleModelRepository.findAllByVehicleMake(vehicleMake);
    }

    @Override
    public List<VehicleModel> listAllVehicleModelsByName(String vehicleModelName) {
        return vehicleModelRepository.findAllByVehicleModelName(vehicleModelName);
    }

    @Override
    public List<VehicleModel> listAllVehicleModelsByMake(VehicleMake vehicleMake) {
        return vehicleModelRepository.findAllByVehicleMake(vehicleMake);
    }
}
