package com.navi.project.carrental.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AddBranchRequest {

    private String id;
    private List<String> vehicleTypes;
}
