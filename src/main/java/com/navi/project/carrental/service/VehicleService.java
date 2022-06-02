package com.navi.project.carrental.service;

import com.navi.project.carrental.entity.Branch;
import com.navi.project.carrental.entity.Vehicle;
import com.navi.project.carrental.entity.VehicleBookingSlot;
import com.navi.project.carrental.model.AddVehicleRequest;
import com.navi.project.carrental.model.VehicleBookingRequest;
import com.navi.project.carrental.model.VehicleReturnRequest;
import com.navi.project.carrental.repository.VehicleRepository;
import com.navi.project.carrental.repository.VehicleSlotBookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class VehicleService {

    @Autowired
    VehicleRepository vehicleRepository;

    @Autowired
    VehicleSlotBookingRepository vehicleSlotBookingRepository;

    @Autowired
    BranchService branchService;


    public List<Vehicle> getWithAdvanceSearch(String branchId, String startTime, String endTime) {
        List<Vehicle> vehicleList = getVehiclesInBranch(branchId);
        if(!vehicleList.isEmpty()) {
            return filterVehiclesWithFreeSlot(vehicleList, LocalTime.parse(startTime), LocalTime.parse(endTime));
        }
        return new ArrayList<>();
    }

    private List<Vehicle> getVehiclesInBranch(String branchId) {
        return vehicleRepository.findByBranch_Id(branchId);
    }

    public void addVehicleToBranch(AddVehicleRequest addVehicleRequest) {

        Optional<Branch> branch = branchService.findBranchById(addVehicleRequest.getBranchId());

        if(branch.isEmpty()) {

        } else {
            if (branch.get().getVehicleTypes().contains(addVehicleRequest.getType())) {
                Vehicle vehicle = new Vehicle();
                vehicle.setBranch(branch.get());
                vehicle.setId(addVehicleRequest.getVehicleId());
                vehicle.setPrice(addVehicleRequest.getPrice());
                vehicle.setType(addVehicleRequest.getType());
                vehicleRepository.save(vehicle);
            } else {
                System.out.println("This Type Of Vehicle is Not Available in Branch");
            }

        }

    }

    public void bookVehicle(VehicleBookingRequest vehicleBookingRequest) {
        //verify if time slow
        List<Vehicle> vehicleList = getVehiclesInBranchUsingType(vehicleBookingRequest);
        int totalVehicleCount = vehicleList.size();
        int availableVehicleCount;
        if (!vehicleList.isEmpty()) {
            LocalTime startTime = LocalTime.parse(vehicleBookingRequest.getStartTime());
            LocalTime endTime = LocalTime.parse(vehicleBookingRequest.getEndTime());
            vehicleList = filterVehiclesWithFreeSlot(vehicleList, startTime, endTime);
            availableVehicleCount = vehicleList.size();
            if (!vehicleList.isEmpty()) {
                System.out.println("Success Booking");
                VehicleBookingSlot vehicleBookingSlot = new VehicleBookingSlot();
                vehicleBookingSlot.setStartTime(startTime);
                vehicleBookingSlot.setEndTime(endTime);
                vehicleBookingSlot.setVehicle(vehicleList.get(0));
                vehicleBookingSlot.setPrice(dynamicPricing(totalVehicleCount, availableVehicleCount, vehicleList.get(0).getPrice()));
                vehicleSlotBookingRepository.save(vehicleBookingSlot);

            } else {
                System.out.println("Vehicle Already Booked");
            }
        } else {
            System.out.println("No Vehicles Available in Branch For Given Type");
        }

    }

    private double dynamicPricing(int totalVehicleCount, int availableVehicleCount, double price) {
        if (availableVehicleCount / totalVehicleCount < 0.2) {
            return price + price * 0.1;
        } else return price;
    }

    //10-12,10-11,Free need to book for 10-11
    private List<Vehicle> filterVehiclesWithFreeSlot(List<Vehicle> vehicleList, LocalTime startTime, LocalTime endTime) {

        List<Vehicle> vehicleWithFreeSlot = new ArrayList<>();

        for (Vehicle vehicle : vehicleList) {
            List<VehicleBookingSlot> vehicleBookingSlotList = vehicle.getVehicleBookingSlotList();
            boolean isSlotFree = true;
            for (VehicleBookingSlot vehicleBookingSlot : vehicleBookingSlotList) {
                if ((vehicleBookingSlot.getStartTime().isAfter(startTime) && vehicleBookingSlot.getEndTime().isBefore(endTime)) ||
                        vehicleBookingSlot.getEndTime().isAfter(startTime) && vehicleBookingSlot.getStartTime().isBefore(endTime)
                ) {
                    isSlotFree = false;
                }
            }
            if (isSlotFree) {
                vehicleWithFreeSlot.add(vehicle);
            }
        }


        return vehicleWithFreeSlot.stream().sorted(Comparator.comparingDouble(Vehicle::getPrice)).toList();
    }

    private List<Vehicle> getVehiclesInBranchUsingType(VehicleBookingRequest vehicleBookingRequest) {
       return vehicleRepository.findByBranch_IdAndType(vehicleBookingRequest.getBranchId(), vehicleBookingRequest.getVehicleType());
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
