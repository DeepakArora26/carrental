package com.navi.project.carrental.service;

import com.navi.project.carrental.entity.Branch;
import com.navi.project.carrental.entity.BranchVehicleMapping;
import com.navi.project.carrental.model.AddBranchRequest;
import com.navi.project.carrental.repository.BranchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BranchService {

    @Autowired
    BranchRepository branchRepository;

    public List<Branch> getAllBranches() {
        return branchRepository.findAll();
    }

    public Optional<Branch> addNewBranch(AddBranchRequest addBranchRequest) {

        Branch branch = new Branch();
        branch.setId(addBranchRequest.getId());

        List<BranchVehicleMapping> branchVehicleMappings = new ArrayList<>();
        for(String vehicleType: addBranchRequest.getVehicleTypes()) {
            BranchVehicleMapping branchVehicleMapping = new BranchVehicleMapping();
            branchVehicleMapping.setVehicleType(vehicleType);
            branchVehicleMapping.setBranch(branch);
            branchVehicleMapping.setId(branch.getId().concat("_").concat(vehicleType));
            branchVehicleMappings.add(branchVehicleMapping);
        }

        branch.setVehicleTypes(branchVehicleMappings);

        return Optional.ofNullable(branchRepository.save(branch));
    }

    public Optional<Branch> findBranchById(String branchId){
        return branchRepository.findById(branchId);
    }
}
