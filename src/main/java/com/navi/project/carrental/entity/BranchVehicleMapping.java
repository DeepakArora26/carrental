package com.navi.project.carrental.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class BranchVehicleMapping {
    @Id
    private String id;
    @ManyToOne
    @JoinColumn(name = "branch_id")
    @JsonIgnore
    private Branch branch;
    private String vehicleType;
}
