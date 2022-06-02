package com.navi.project.carrental.service;

import com.navi.project.carrental.entity.Branch;
import com.navi.project.carrental.entity.Vehicle;
import com.navi.project.carrental.model.AddVehicleRequest;
import com.navi.project.carrental.model.VehicleBookingRequest;
import com.navi.project.carrental.model.VehicleReturnRequest;
import com.navi.project.carrental.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
public class VehicleService {

    @Autowired
    VehicleRepository vehicleRepository;

    @Autowired
    BranchService branchService;

    @Autowired

    public List<Vehicle> getWithAdvanceSearch(String branchId, String startTime, String endTime) {
        return vehicleRepository.findByBranch_IdAndVehicleBookingSlotList_StartTimeAndVehicleBookingSlotList_EndTime(branchId, LocalTime.parse(startTime), LocalTime.parse(endTime));
    }

    public void addVehicleToBranch(AddVehicleRequest addVehicleRequest) {

        Optional<Branch> branch = branchService.findBranchById(addVehicleRequest.getBranchId());

        if(branch.isEmpty()) {

        } else {
            Vehicle vehicle = new Vehicle();
            vehicle.setBranch(branch.get());
            vehicle.setId(addVehicleRequest.getVehicleId());
            vehicle.setPrice(addVehicleRequest.getPrice());
            vehicle.setType(addVehicleRequest.getType());
            vehicleRepository.save(vehicle);
        }

    }

    public void bookVehicle(VehicleBookingRequest vehicleBookingRequest) {

      if(isVehicleAvailableInBranch(vehicleBookingRequest)) {
          if(isVehicleSlotFree(vehicleBookingRequest)) {
              //book
          }
      }

    }

    private boolean isVehicleSlotFree(VehicleBookingRequest vehicleBookingRequest) {
        return true;
    }

    private boolean isVehicleAvailableInBranch(VehicleBookingRequest vehicleBookingRequest) {
        return true;
    }

    public void returnVehicle(VehicleReturnRequest vehicleReturnRequest) {
        if(isVehicleAtRightBranch(vehicleReturnRequest)) {
            //return complete
        }
    }

    private boolean isVehicleAtRightBranch(VehicleReturnRequest vehicleReturnRequest) {
        return true;
    }
}
