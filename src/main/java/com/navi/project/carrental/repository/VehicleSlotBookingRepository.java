package com.navi.project.carrental.repository;

import com.navi.project.carrental.entity.VehicleBookingSlot;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehicleSlotBookingRepository extends JpaRepository<VehicleBookingSlot, Integer>  {
}
