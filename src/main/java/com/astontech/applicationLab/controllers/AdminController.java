package com.astontech.applicationLab.controllers;

import com.astontech.applicationLab.domain.VO.VehicleVO;
import com.astontech.applicationLab.domain.Vehicle;
import com.astontech.applicationLab.domain.VehicleMake;
import com.astontech.applicationLab.domain.VehicleModel;
import com.astontech.applicationLab.services.VehicleMakeService;
import com.astontech.applicationLab.services.VehicleModelService;
import com.astontech.applicationLab.services.VehicleService;
import com.sun.xml.internal.bind.v2.TODO;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class AdminController {

    @Autowired
    private VehicleMakeService vehicleMakeService;

    @Autowired
    private VehicleModelService vehicleModelService;

    @Autowired
    private VehicleService vehicleService;

    private Logger log = Logger.getLogger(AdminController.class);

    //region HOME
    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String adminHome() {
        return "admin/adminHome";
    }
    //endregion

    //region ADD VEHICLES
    @RequestMapping(value = "/admin/vehicle/add", method = RequestMethod.GET)
    public String adminVehicleGet(Model model) {
        model.addAttribute("vehicleVO", new VehicleVO());
        model.addAttribute("warningAlert", "visible");

        return "admin/vehicle/vehicle_add";
    }

    @RequestMapping(value = "/admin/vehicle/add", method = RequestMethod.POST)
    public String adminVehiclePost(VehicleVO vehicleVo, Model model) {
        vehicleVo.splitNewElementsIntoArray();
        logVehicleVo(vehicleVo);

       saveVehicleFromVO(vehicleVo);

       if(!checkForEmptyFields("", vehicleVo))
           model.addAttribute("successAlert", "visible");
       else
           model.addAttribute("errorAlert", "visible");

        return "admin/vehicle/vehicle_add";
    }

    //endregion

    //region LIST ALL VEHICLES
    @RequestMapping(value = "/admin/vehicle/listAll", method = RequestMethod.GET)
    public String adminVehicleList(Model model) {
        model.addAttribute("vehicleList", vehicleMakeService.listAllVehicleMakes());
        model.addAttribute("vehicleList", vehicleModelService.listAllVehicleModels());
        model.addAttribute("vehicleList", vehicleService.listAllVehicles());

       return "admin/vehicle/vehicle_listVehicles";
    }

    //endregion

    //region LIST ALL MODELS
    @RequestMapping(value = "/admin/vehicleModel/listModels", method = RequestMethod.GET)
    public String adminVehicleModelList(Model model) {
        model.addAttribute("vehicleModelList", vehicleMakeService.listAllVehicleMakes());
        model.addAttribute("vehicleModelList", vehicleModelService.listAllVehicleModels());

        return "admin/vehicleModel/vehicleModel_listModels";
    }
    //endregion

    //region LIST ALL MAKES
    @RequestMapping(value = "/admin/vehicleMake/listMakes", method = RequestMethod.GET)
    public String adminVehicleMakeList(Model model) {
        model.addAttribute("vehicleMakeList", vehicleMakeService.listAllVehicleMakes());

        return "admin/vehicleMake/vehicleMake_listMakes";
    }

    //endregion

    //region LIST VEHICLES SPECIFIED BY MODEL

    @RequestMapping(value = "/admin/vehicle/listModelVehicles/{id}", method = RequestMethod.GET)
    public String adminModelVehicleGet(Model model, @PathVariable("id") int vehicleModelId) {

        // Get Id of Model
        VehicleModel vehicleModel = vehicleModelService.getVehicleModelById(vehicleModelId);

        // Get Model Name from Model
        String vehicleModelName = vehicleModel.getVehicleModelName();
        // Get Make Name from Model
        String vehicleModelMake = vehicleModel.getVehicleMake().getVehicleMakeName();


        model.addAttribute("vehicleList", vehicleService.listAllVehiclesByModel(vehicleModel));
        model.addAttribute("vehicleModelName", vehicleModelName);
        model.addAttribute("vehicleMakeName", vehicleModelMake);

        return "admin/vehicle/vehicle_listModelVehicles";
    }

    //endregion

    //region LIST Vehicles SPECIFIED BY MAKE

     @RequestMapping(value = "/admin/vehicle/listMakeVehicles/{id}", method = RequestMethod.GET)
     private String adminMakeModelsGet(@PathVariable("id") int vehicleMakeId, Model model) {

        // Get Make ID then get model list based on that Id
        VehicleMake vehicleMake = vehicleMakeService.getVehicleMakeById(vehicleMakeId);
        List<VehicleModel> vehicleModelList = vehicleModelService.getAllVehicleModelsByMake(vehicleMake);

        // Get all vehicles based on Model index then add to vehicleList
         List<Vehicle> vehicleList = new ArrayList<>();
         for (int i = 0; i < vehicleModelList.size(); i++) {
             vehicleService.listAllVehiclesByModel(vehicleModelList.get(i)).forEach(vehicleList::add);
         }

         model.addAttribute("vehicleMakeModelList", vehicleList);

         // Get vehicleMakeName to populate the title
         String vehicleMakeName = vehicleMake.getVehicleMakeName();

         model.addAttribute("vehicleMakeName", vehicleMakeName);


        return "admin/vehicle/vehicle_listMakeVehicles";
     }

    //endregion

    //region EDIT VEHICLE MAKE
    @RequestMapping(value = "/admin/vehicleMake/edit/{id}", method = RequestMethod.GET)
    public String adminVehicleMakeEdit(@PathVariable("id") int vehicleMakeId, Model model) {
        VehicleMake vehicleMake = vehicleMakeService.getVehicleMakeById(vehicleMakeId);

        model.addAttribute("vehicleMake", vehicleMake);
        return "admin/vehicleMake/vehicleMake_edit";
    }

    //endregion

    //region EDIT VEHICLE MODELS
    @RequestMapping(value = "/admin/vehicleModel/edit/{id}", method = RequestMethod.GET)
    public String adminVehicleModelEdit(@PathVariable("id") int vehicleMakeId, Model model) {

        VehicleMake vehicleMake = vehicleMakeService.getVehicleMakeById(vehicleMakeId);
        VehicleVO vehicleVO = new VehicleVO();
        vehicleVO.setVehicleModelList(vehicleModelService.getAllVehicleModelsByMake(vehicleMake));
        vehicleVO.setVehicleMake(vehicleMake);

        model.addAttribute("vehicleVO", vehicleVO);

        return "admin/vehicleModel/vehicleModel_edit";
    }

    //endregion

    //region EDIT VEHICLES
    @RequestMapping(value = "/admin/vehicle/edit/{id}", method = RequestMethod.GET)
    public String adminVehicleEdit(@PathVariable("id") int vehicleId, Model model) {


        VehicleVO vehicleVO = new VehicleVO();
        vehicleVO.setVehicle(vehicleService.getVehicleById(vehicleId));
        vehicleVO.setVehicleModel(vehicleModelService.getVehicleModelById(vehicleVO.getVehicle().getVehicleModel().getVehicleModelId()));
        vehicleVO.setVehicleMake(vehicleMakeService.getVehicleMakeById(vehicleVO.getVehicleModel().getVehicleMake().getVehicleMakeId()));

        model.addAttribute("vehicleVO", vehicleVO);



        return "admin/vehicle/vehicle_edit";
    }
    //endregion

    //region UPDATE VEHICLE MAKE
    @RequestMapping(value = "/admin/vehicleMake/update", method = RequestMethod.POST)
    public String vehicleMakeUpdate(VehicleMake vehicleMake, @RequestParam("vehicleMakeName") String newVehicleMakeName) {

        if(!newVehicleMakeName.equals("")) {
            vehicleMakeService.saveVehicleMake(vehicleMake);
            log.info("Vehicle Make Changed To: " + newVehicleMakeName);
        } else {
            log.info("Blank field is not allowed");
            //todo throw error here for user "no blank fields"
        }

        return "redirect:/admin/vehicleMake/edit/" + vehicleMake.getVehicleMakeId();
    }

    //endregion

    //region UPDATE VEHICLE MODELS FOR THEIR MAKE

    @RequestMapping(value = "/admin/vehicleModel/update", method = RequestMethod.POST)
    public String adminVehicleModelUpdate(VehicleVO vehicleVO, @RequestParam("inputNewVehicleModel") String newVehicleModelName) {

        VehicleModel newVehicleModel = new VehicleModel();
        if(!newVehicleModelName.equals("")) {
            newVehicleModel.setVehicleModelName(newVehicleModelName);
            newVehicleModel.setVehicleMake(vehicleVO.getVehicleMake());
            vehicleModelService.saveVehicleModel(newVehicleModel);
        }

        //notes:    iterate thru the list of elements
        for(int i = 0; i < vehicleVO.getVehicleModelList().size(); i++) {
            System.out.println(vehicleVO.getVehicleModelList().get(i).getVehicleModelName());
            //notes:    check to see if element name is empty
            if(vehicleVO.getVehicleModelList().get(i).getVehicleModelName().equals("")) {
                ///notes:   element name is blank remove it from the list
                vehicleVO.getVehicleModelList().remove(i);
            }
            vehicleVO.getVehicleModelList().get(i).setVehicleMake(vehicleVO.getVehicleMake());
        }


        vehicleModelService.saveVehicleModel(vehicleVO.getVehicleModelList());

        return "redirect:/admin/vehicleModel/edit/" + vehicleVO.getVehicleMake().getVehicleMakeId();
    }

    //endregion

    //region UPDATE VEHICLE
    @RequestMapping(value = "/admin/vehicle/update", method = RequestMethod.POST)
    public String adminVehicleUpdate(VehicleVO vehicleVO) {

        if(!checkForEmptyFields("", vehicleVO)) {


            checkIfVehicleMakeExistAndAdd(vehicleVO);

            checkIfVehicleModelExistAndAdd(vehicleVO);

            Vehicle vehicle = vehicleService.getVehicleById(vehicleVO.getVehicle().getVehicleId());
            updateVehicleDetails(vehicleVO, vehicle);

            vehicleService.saveVehicle(vehicle);

            logUpdatedVehicleVO(vehicleVO);

        } else
            log.info("You cannot have blank fields");

        return "redirect:/admin/vehicle/edit/" + vehicleVO.getVehicle().getVehicleId();
    }

    //endregion

    //region DELETE VEHICLES
    @RequestMapping(value = "admin/vehicle/delete/{id}", method = RequestMethod.GET)
    public String adminDeleteVehicle(@PathVariable("id") int vehicleId) {
        vehicleService.deleteVehicle(vehicleId);
        return "redirect:/admin/vehicle/listAll";
    }
    //endregion

    //region DELETE VEHICLES OF SPECIFIC MODEL
    @RequestMapping(value = "admin/vehicleModel/deleteVehicle/{id}", method = RequestMethod.GET)
    public String adminDeleteModelVehicle(@PathVariable("id") int vehicleId) {

        Vehicle vehicle = vehicleService.getVehicleById(vehicleId);
        vehicleService.deleteVehicle(vehicleId);

        return "redirect:/admin/vehicle/listModelVehicles/" + vehicle.getVehicleModel().getVehicleModelId();
    }
    //endregion

    //region DELETE MODELS
    @RequestMapping(value = "admin/vehicleModel/delete/{id}", method = RequestMethod.GET)
    public String adminDeleteModel(@PathVariable("id") int vehicleModelId) {

        VehicleModel vehicleModel = vehicleModelService.getVehicleModelById(vehicleModelId);
        List<Vehicle> vehicleList = vehicleService.listAllVehiclesByModel(vehicleModel);

        for(Vehicle vehicle : vehicleList) {
            vehicleService.deleteVehicle(vehicle.getVehicleId());
        }
        vehicleModelService.deleteVehicleModel(vehicleModelId);

        return "redirect:/admin/vehicle/listModels";
    }
    //endregion

    //region DELETE MAKE
    @RequestMapping(value = "/admin/vehicleMake/delete/{id}", method = RequestMethod.GET)
    public String adminDeleteMake(@PathVariable("id") int vehicleMakeId) {

        VehicleMake vehicleMake = vehicleMakeService.getVehicleMakeById(vehicleMakeId);
        List<VehicleModel> vehicleModelList = vehicleModelService.listAllVehicleModelsByMake(vehicleMake);



        for(VehicleModel vehicleModel : vehicleModelList) {
            List<Vehicle> vehicleList = vehicleService.listAllVehiclesByModel(vehicleModel);
            for(Vehicle vehicle :  vehicleList) {
                vehicleService.deleteVehicle(vehicle.getVehicleId());
            }
            vehicleModelService.deleteVehicleModel(vehicleModel.getVehicleModelId());
        }
        vehicleMakeService.deleteVehicleMake(vehicleMakeId);
        return "redirect:/admin/vehicleMake/listMakes";
    }
    //endregion

    //region DELETE VEHICLES BY MAKE
    @RequestMapping(value = "admin/vehicleMake/deleteMakeModels/{id}", method = RequestMethod.GET)
    public String adminDeleteMakeModels(@PathVariable("id") int vehicleId) {

        Vehicle vehicle = vehicleService.getVehicleById(vehicleId);
        vehicleService.deleteVehicle(vehicleId);

        System.out.println(vehicle.getVehicleModel().getVehicleMake().getVehicleMakeId());

        return "redirect:/admin/vehicle/listMakeModels/" + vehicle.getVehicleModel().getVehicleMake().getVehicleMakeId() ;
    }
    //endregion






    private void saveVehicleFromVO(VehicleVO vehicleVO) {
        List<Vehicle> vehicleList = vehicleService.listAllVehicles();
        // check for empty fields, if none, check and add input
        if(!checkForEmptyFields("", vehicleVO)) {
            if(!checkIfVehicleExists(vehicleVO, vehicleList) ) {
                checkAndAddVehicleMake(vehicleVO);
                checkAndAddVehicleModel(vehicleVO);
                addVehicleDetails(vehicleVO, vehicleList);
                log.info("Vehicle successfully recorded");
            } else {
                log.info("Vehicle already exists in database");
                //todo throw warning saying "vehicle already exists"
            }
        }
        else {
            log.info("Some fields were blank, please fill all");
            //todo throw warning saying "no empty boxes"
        }

    }

    private boolean checkForEmptyFields(String blankField, VehicleVO vehicleVO) {
        List<String> vehicleVOList = new ArrayList<>();
        vehicleVOList.add(vehicleVO.getNewVehicleMake());
        vehicleVOList.add(vehicleVO.getNewVehicleModel());
        vehicleVOList.add(vehicleVO.getNewVin());
        vehicleVOList.add(vehicleVO.getNewLicensePlate());
        vehicleVOList.add(vehicleVO.getNewYear());
        vehicleVOList.add(vehicleVO.getNewColor());

        return vehicleVOList.stream().filter(blankField::equals).findFirst().isPresent();
    }

    private boolean checkIfVehicleExists(VehicleVO vehicleVO, List<Vehicle> vehicleList) {

        boolean vehicleExists = true;
        if(containsVehicleVin(vehicleList, vehicleVO.getNewVin())) {
            // todo add warning label
            return vehicleExists;
        } else
            vehicleExists = false;
            return  vehicleExists;
    }

    private void checkAndAddVehicleMake(VehicleVO vehicleVO) {
        List<VehicleMake> vehicleMakeList = vehicleMakeService.listAllVehicleMakes();
        if(containsVehicleMakeName(vehicleMakeList, vehicleVO.getNewVehicleMake()) == false) {
            VehicleMake vehicleMake = new VehicleMake(vehicleVO.getNewVehicleMake());
            vehicleMakeService.saveVehicleMake(vehicleMake);
        }
    }

    private void checkAndAddVehicleModel(VehicleVO vehicleVO) {
        List<VehicleModel> vehicleModelList = vehicleModelService.listAllVehicleModels();
        if(containsVehicleModel(vehicleModelList, vehicleVO.getNewVehicleModel()) == false) {
            VehicleMake existingVehicleMake = vehicleMakeService.findByVehicleMakeName(vehicleVO.getNewVehicleMake());
            VehicleModel vehicleModel = new VehicleModel(vehicleVO.getNewVehicleModel(), existingVehicleMake);
            vehicleModelService.saveVehicleModel(vehicleModel);
        }
    }

    private void addVehicleDetails(VehicleVO vehicleVO, List<Vehicle> vehicleList) {
        VehicleModel vehicleModel = vehicleModelService.getVehicleModelByName(vehicleVO.getNewVehicleModel());
        vehicleList.add(new Vehicle(vehicleVO.getNewLicensePlate(), vehicleVO.getNewVin(), Integer.parseInt(vehicleVO.getNewYear()), vehicleVO.getNewColor(), vehicleModel));
        vehicleService.saveVehicle(vehicleList);
    }

    private boolean containsVehicleModel(List<VehicleModel> vehicleModelList, String newVehicleModelName) {
        return vehicleModelList.stream().map(VehicleModel::getVehicleModelName).filter(newVehicleModelName::equals).findFirst().isPresent();
    }

    private boolean containsVehicleVin(List<Vehicle> vehicleList, String newVehicleVin){
        return vehicleList.stream().map(Vehicle::getVin).filter(newVehicleVin::equals).findFirst().isPresent();
    }

    private boolean containsVehicleMakeName(List<VehicleMake> vehicleMakeList, String newVehicleMakeName){
        return vehicleMakeList.stream().map(VehicleMake::getVehicleMakeName).filter(newVehicleMakeName::equals).findFirst().isPresent();
    }

    private void updateVehicleDetails(VehicleVO vehicleVO, Vehicle vehicle) {

        vehicle.setVehicleName(vehicleVO.getVehicle().getVehicleName());
        vehicle.setLicensePlate(vehicleVO.getVehicle().getLicensePlate());
        vehicle.setVin(vehicleVO.getVehicle().getVin());
        vehicle.setYear(vehicleVO.getVehicle().getYear());
        vehicle.setColor(vehicleVO.getVehicle().getColor());
        vehicle.setVehicleModel(vehicleModelService.getVehicleModelByName(vehicleVO.getVehicle().getVehicleModel().getVehicleModelName()));
        vehicle.getVehicleModel().setVehicleMake(vehicleMakeService.findByVehicleMakeName(vehicleVO.getVehicle().getVehicleModel().getVehicleMake().getVehicleMakeName()));
    }

    private void checkIfVehicleMakeExistAndAdd(VehicleVO  vehicleVO) {
        VehicleMake newVehicleMake = new VehicleMake();
        if(vehicleMakeService.findByVehicleMakeName(vehicleVO.getVehicle().getVehicleModel().getVehicleMake().getVehicleMakeName()) == null) {
            newVehicleMake.setVehicleMakeName(vehicleVO.getVehicle().getVehicleModel().getVehicleMake().getVehicleMakeName());
            vehicleMakeService.saveVehicleMake(newVehicleMake);
        }
    }

    private void checkIfVehicleModelExistAndAdd(VehicleVO vehicleVO) {
        VehicleModel newVehicleModel = new VehicleModel();
        if (vehicleModelService.findByVehicleModelName(vehicleVO.getVehicle().getVehicleModel().getVehicleModelName()) == null) {
            newVehicleModel.setVehicleModelName(vehicleVO.getVehicle().getVehicleModel().getVehicleModelName());
            newVehicleModel.setVehicleMake(vehicleMakeService.findByVehicleMakeName(vehicleVO.getVehicle().getVehicleModel().getVehicleMake().getVehicleMakeName()));
            vehicleModelService.saveVehicleModel(newVehicleModel);
        }
    }


    private void logVehicleVo(VehicleVO vehicleVO) {
        log.info("New Vehicle Make: " + vehicleVO.getNewVehicleMake());
        log.info("New Vehicle Model: " + vehicleVO.getNewVehicleModel());
        log.info("New License Plate: " + vehicleVO.getNewLicensePlate());
        log.info("New VIN: " + vehicleVO.getNewVin());
        log.info("New Year: " + vehicleVO.getNewYear());
        log.info("New Color: " + vehicleVO.getNewColor());

    }

    private void logUpdatedVehicleVO(VehicleVO vehicleVO) {

        log.info("Vehicle successfully updated");
        log.info("Updated Vehicle Make: " + vehicleVO.getVehicle().getVehicleModel().getVehicleMake().getVehicleMakeName());
        log.info("Updated Vehicle Model: " + vehicleVO.getVehicle().getVehicleModel().getVehicleModelName());
        log.info("Updated License Plate: " + vehicleVO.getVehicle().getLicensePlate());
        log.info("Updated VIN: " + vehicleVO.getVehicle().getVin());
        log.info("Updated Year: " + vehicleVO.getVehicle().getYear());
        log.info("Updated Color: " + vehicleVO.getVehicle().getColor());
    }

}
