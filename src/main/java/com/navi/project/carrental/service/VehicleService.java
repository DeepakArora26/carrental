package com.navi.project.carrental.service;

import com.navi.project.carrental.entity.Vehicle;
import com.navi.project.carrental.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.util.List;

@Service
public class VehicleService {

    @Autowired
    VehicleRepository vehicleRepository;

    public List<Vehicle> getWithAdvanceSearch(String branchId, Time startTime, Time endTime) {
        return null;
    }
}
