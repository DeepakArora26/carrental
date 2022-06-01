package com.navi.project.carrental.controller;

import com.navi.project.carrental.entity.Vehicle;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "vehicle")
public class VehicleController {

    @GetMapping("")
    public ResponseEntity<List<Vehicle>> getVehicleBasedOnBranchAndTime() {
        return null;
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
