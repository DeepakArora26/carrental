package com.navi.project.carrental.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddVehicleRequest {
    private String type;
    private double price;
    private String branchId;
    private String vehicleId;
}
