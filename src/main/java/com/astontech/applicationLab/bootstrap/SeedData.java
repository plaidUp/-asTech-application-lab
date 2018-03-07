package com.astontech.applicationLab.bootstrap;

import com.astontech.applicationLab.domain.Vehicle;
import com.astontech.applicationLab.domain.VehicleMake;
import com.astontech.applicationLab.domain.VehicleModel;
import com.astontech.applicationLab.repositories.VehicleModelRepository;
import com.astontech.applicationLab.repositories.VehicleRepository;
import com.astontech.applicationLab.services.VehicleMakeService;
import com.astontech.applicationLab.services.VehicleModelService;
import com.astontech.applicationLab.services.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SeedData implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private VehicleModelService vehicleModelService;

    @Autowired
    private VehicleMakeService vehicleMakeService;

    @Autowired
    private VehicleService vehicleService;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        generateVehicleModelAndVehicleMakes();
    }

    private void generateVehicleModelAndVehicleMakes() {

        //region ADD MAKES
        VehicleMake fordType = new VehicleMake("Ford");
        VehicleMake chevyType = new VehicleMake("Chevy");
        vehicleMakeService.saveVehicleMake(fordType);
        vehicleMakeService.saveVehicleMake(chevyType);

        //endregion

        //region ADD NEW VEHICLE MODEL NAME
        List<VehicleModel> fordList = new ArrayList<>();
        fordList.add(new VehicleModel("Mustang", fordType));
        fordList.add(new VehicleModel("Focus", fordType));
        fordList.add(new VehicleModel("F-150", fordType));
        fordList.add(new VehicleModel("Fusion", fordType));

        List<VehicleModel> chevyList = new ArrayList<>();
        chevyList.add(new VehicleModel("Corvette", chevyType));
        chevyList.add(new VehicleModel("Silverado", chevyType));
        chevyList.add(new VehicleModel("Colorado", chevyType));
        chevyList.add(new VehicleModel("Cobalt", chevyType));

        vehicleModelService.saveVehicleModel(chevyList);
        vehicleModelService.saveVehicleModel(fordList);

        //endregion

        //region GET VEHICLE MODEL BY NAME
        VehicleModel mustang = vehicleModelService.getVehicleModelByName("Mustang");
        VehicleModel fusion = vehicleModelService.getVehicleModelByName("Fusion");
        VehicleModel f150 = vehicleModelService.getVehicleModelByName("F-150");
        VehicleModel focus = vehicleModelService.getVehicleModelByName("Focus");
        VehicleModel corvette = vehicleModelService.getVehicleModelByName("Corvette");
        VehicleModel silverado = vehicleModelService.getVehicleModelByName("Silverado");
        VehicleModel colorado = vehicleModelService.getVehicleModelByName("Colorado");
        VehicleModel cobalt = vehicleModelService.getVehicleModelByName("Cobalt");

        //endregion

        //region ADD ALL VEHICLES
        List<Vehicle> vehicleList = new ArrayList<>();
        vehicleList.add(new Vehicle("DDD123", "JN1CV6AR4CM673371", 1991, "Blue", mustang));
        vehicleList.add(new Vehicle("DNA143", "JN1CV6AR4CM673372", 2006, "Black", mustang));

        vehicleList.add(new Vehicle("MN0123", "JN1CV6AR4CM673372", 2001, "Red", fusion));
        vehicleList.add(new Vehicle("MN0456", "JN1CV6AR4CM673372", 2021, "Yellow", fusion));

        vehicleList.add(new Vehicle("MN0011", "JN1CV6AR4CM673372", 2011, "Green", f150));
        vehicleList.add(new Vehicle("MN0321", "JN1CV6AR4CM673372", 2001, "Silver", f150));

        vehicleList.add(new Vehicle("MN3311", "JN1CV6AR4CM673372", 2001, "Brown", focus));
        vehicleList.add(new Vehicle("MN0011", "JN1CV6AR4CM673372", 2011, "Green", focus));

        vehicleList.add(new Vehicle("MNV990", "JN1CV6AR4CM673372", 2009, "Teal", silverado));
        vehicleList.add(new Vehicle("MN0011", "JN1CV6AR4CM673372", 2019, "Green", silverado));

        vehicleList.add(new Vehicle("MN0011", "JN1CV6AR4CM673372", 2004, "Purple", corvette));
        vehicleList.add(new Vehicle("MNSV28", "JN1CV6AR4CM673372", 2014, "Green", corvette));

        vehicleList.add(new Vehicle("MN0011", "JN1CV6AR4CM673372", 2006, "Grey", cobalt));
        vehicleList.add(new Vehicle("MN0000", "JN1CV6AR4CM673372", 2016, "Green", cobalt));

        vehicleList.add(new Vehicle("MN0011", "JN1CV6AR4CM673372", 2007, "Blue", colorado));
        vehicleList.add(new Vehicle("MNNM12", "JN1CV6AR4CM673372", 2010, "Green", colorado));

        vehicleService.saveVehicle(vehicleList);

        //endregion




    }
}
