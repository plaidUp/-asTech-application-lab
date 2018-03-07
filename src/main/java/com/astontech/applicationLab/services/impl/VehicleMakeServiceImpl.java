package com.astontech.applicationLab.services.impl;

import com.astontech.applicationLab.domain.VehicleMake;
import com.astontech.applicationLab.repositories.VehicleMakeRepository;
import com.astontech.applicationLab.services.VehicleMakeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleMakeServiceImpl implements VehicleMakeService {

    @Autowired
    private VehicleMakeRepository vehicleMakeRepository;

    @Override
    public List<VehicleMake> listAllVehicleMakes() {
        return vehicleMakeRepository.findAll();
    }

    @Override
    public VehicleMake getVehicleMakeById(Integer id) {
        return vehicleMakeRepository.findOne(id);
    }

    @Override
    public VehicleMake saveVehicleMake(VehicleMake vehicleMake) {
        return vehicleMakeRepository.save(vehicleMake);
    }

    @Override
    public Iterable<VehicleMake> saveVehicleMake(Iterable<VehicleMake> vehicleMakeIterable) {
        return vehicleMakeRepository.save(vehicleMakeIterable);
    }

    @Override
    public void deleteVehicleMake(Integer id) {
        vehicleMakeRepository.delete(id);
    }

    @Override
    public VehicleMake findByVehicleMakeName(String vehicleMakeName) {
        return vehicleMakeRepository.findByVehicleMakeName(vehicleMakeName);
    }
}
