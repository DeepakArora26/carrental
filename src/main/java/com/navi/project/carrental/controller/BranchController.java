package com.navi.project.carrental.controller;

import com.navi.project.carrental.entity.Branch;
import com.navi.project.carrental.model.AddBranchRequest;
import com.navi.project.carrental.service.BranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("branch")
public class BranchController {

    @Autowired
    BranchService branchService;

    @PostMapping
    public ResponseEntity<String> addBranch(@RequestBody AddBranchRequest addBranchRequest) {
        Optional<Branch> branch = branchService.addNewBranch(addBranchRequest);
        if (branch.isEmpty()) {
            return new ResponseEntity<>("Not Created", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("Created", HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Branch>> getBranches() {
        List<Branch> branchList = branchService.getAllBranches();
        if (branchList.isEmpty()) {
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(branchList, HttpStatus.OK);
    }
}
