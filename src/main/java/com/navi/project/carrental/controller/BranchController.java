package com.navi.project.carrental.controller;

import com.navi.project.carrental.entity.Branch;
import com.navi.project.carrental.service.BranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("branch")
public class BranchController {

    @Autowired
    BranchService branchService;

    @PostMapping
    public boolean addBranch() {
        return true;
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
