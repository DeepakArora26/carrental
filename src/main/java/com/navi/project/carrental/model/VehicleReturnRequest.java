package com.navi.project.carrental.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VehicleReturnRequest {
    private String branchId;
    private String bookingId;
}
