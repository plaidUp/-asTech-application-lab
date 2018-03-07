package com.astontech.applicationLab.repositories;

import com.astontech.applicationLab.domain.VehicleMake;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface VehicleMakeRepository extends CrudRepository<VehicleMake, Integer> {

    VehicleMake findByVehicleMakeName(String vehicleMakeName);

    List<VehicleMake> findAll();
}
