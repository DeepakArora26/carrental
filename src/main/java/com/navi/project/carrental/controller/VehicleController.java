package com.navi.project.carrental.controller;

import com.navi.project.carrental.entity.Vehicle;
import com.navi.project.carrental.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "vehicle")
public class VehicleController {

    @Autowired
    VehicleService vehicleService;

    @GetMapping
    public ResponseEntity<List<Vehicle>> getVehicleBasedOnBranchAndTime(@RequestParam String branchId,
                                                                        @RequestParam Time startTime,
                                                                        @RequestParam Time endTime) {
        List<Vehicle> vehicleList = vehicleService.getWithAdvanceSearch(branchId, startTime, endTime);
        if (vehicleList.isEmpty()) {
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(vehicleList, HttpStatus.OK);
    }

    @PostMapping
    public boolean addVehicle() {
        return true;
    }

    @PatchMapping(value = "book")
    public boolean bookVehicle() {
        return true;
    }

    @PatchMapping(value = "return")
    public boolean dropVehicle() {
        return true;
    }
}
