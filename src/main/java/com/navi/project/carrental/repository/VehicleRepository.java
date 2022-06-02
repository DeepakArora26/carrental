package com.navi.project.carrental.repository;

import com.navi.project.carrental.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalTime;
import java.util.List;

public interface VehicleRepository extends JpaRepository<Vehicle, String> {
    List<Vehicle> findByBranch_IdAndVehicleBookingSlotList_StartTimeAndVehicleBookingSlotList_EndTime(String id, LocalTime startTime, LocalTime endTime);

    List<Vehicle> findByBranch_IdAndType(String id, String type);


}
