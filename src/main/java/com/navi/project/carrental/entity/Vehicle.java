package com.navi.project.carrental.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;


@Entity
@Getter
@Setter
@ToString
public class Vehicle {
    @Id
    private String id;
    @ManyToOne
    @JoinColumn(name = "branch_id")
    @JsonIgnore
    private Branch branch;
    private String type;
    private double price;
    @OneToMany(mappedBy = "vehicle", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private List<VehicleBookingSlot> vehicleBookingSlotList;
}
