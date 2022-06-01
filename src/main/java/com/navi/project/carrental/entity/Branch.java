package com.navi.project.carrental.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
public class Branch {
    @Id
    private String id;
    private String name;
    @OneToMany(mappedBy = "branch", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private List<BranchVehicleMapping> vehicleTypes;
    @OneToMany(mappedBy = "branch", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private List<Vehicle> vehicleList;


}
