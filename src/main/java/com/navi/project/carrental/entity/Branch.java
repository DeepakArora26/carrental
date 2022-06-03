package com.navi.project.carrental.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Getter
@Setter
public class Branch {
    @Id
    private String id;
    @OneToMany(mappedBy = "branch", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private List<BranchVehicleMapping> vehicleTypes;
    @OneToMany(mappedBy = "branch", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private List<Vehicle> vehicleList;
}
