package com.navi.project.carrental.service;

import com.navi.project.carrental.entity.Branch;
import com.navi.project.carrental.entity.BranchVehicleMapping;
import com.navi.project.carrental.model.AddBranchRequest;
import com.navi.project.carrental.repository.BranchRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BranchService {

    private static Logger logger = LoggerFactory.getLogger(BranchService.class);

    @Autowired
    BranchRepository branchRepository;

    public List<Branch> getAllBranches() {
        return branchRepository.findAll();
    }

    public Optional<Branch> addNewBranch(AddBranchRequest addBranchRequest) {

        Optional<Branch> existingBranch = branchRepository.findById(addBranchRequest.getId());
        if (existingBranch.isEmpty()) {

            Branch branch = new Branch();
            branch.setId(addBranchRequest.getId());

            List<BranchVehicleMapping> branchVehicleMappings = new ArrayList<>();
            for (String vehicleType : addBranchRequest.getVehicleTypes()) {
                branchVehicleMappings.add(getBranchVehicleMapping(branch, vehicleType));
            }
            branch.setVehicleTypes(branchVehicleMappings);
            return Optional.ofNullable(branchRepository.save(branch));
        } else {
            logger.info("FALSE");
            return existingBranch;
        }
    }

    private BranchVehicleMapping getBranchVehicleMapping(Branch branch, String vehicleType) {
        BranchVehicleMapping branchVehicleMapping = new BranchVehicleMapping();
        branchVehicleMapping.setVehicleType(vehicleType);
        branchVehicleMapping.setBranch(branch);
        branchVehicleMapping.setId(branch.getId().concat("_").concat(vehicleType));
        return branchVehicleMapping;
    }

    public Optional<Branch> findBranchById(String branchId) {
        return branchRepository.findById(branchId);
    }
}
