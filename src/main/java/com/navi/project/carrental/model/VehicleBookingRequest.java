package com.navi.project.carrental.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VehicleBookingRequest {
    private String branchId;
    private String vehicleType;
    private String startTime;
    private String endTime;
}
