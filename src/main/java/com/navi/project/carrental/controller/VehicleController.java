package com.navi.project.carrental.controller;

import com.navi.project.carrental.entity.Vehicle;
import com.navi.project.carrental.model.AddVehicleRequest;
import com.navi.project.carrental.model.VehicleBookingRequest;
import com.navi.project.carrental.model.VehicleReturnRequest;
import com.navi.project.carrental.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "vehicle")
public class VehicleController {

    @Autowired
    VehicleService vehicleService;

    @GetMapping
    public ResponseEntity<List<Vehicle>> getVehicleBasedOnBranchAndTime(@RequestParam String branchId,
                                                                        @RequestParam String startTime,
                                                                        @RequestParam String endTime) {
        List<Vehicle> vehicleList = vehicleService.getWithAdvanceSearch(branchId, startTime, endTime);
        if (vehicleList.isEmpty()) {
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(vehicleList, HttpStatus.OK);
    }

    @PostMapping
    public void addVehicle(@RequestBody AddVehicleRequest addVehicleRequest) {
        vehicleService.addVehicleToBranch(addVehicleRequest);

    }

    @PatchMapping(value = "book")
    public boolean bookVehicle(@RequestBody VehicleBookingRequest vehicleBookingRequest) {
        vehicleService.bookVehicle(vehicleBookingRequest);

        return true;
    }

    @PatchMapping(value = "return")
    public boolean dropVehicle(@RequestBody VehicleReturnRequest vehicleReturnRequest) {

        vehicleService.returnVehicle(vehicleReturnRequest);
        return true;
    }
}
