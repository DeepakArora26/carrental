package com.navi.project.carrental.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController(value = "branch")
public class BranchController {

    @PostMapping
    public boolean addBranch() {
        return true;
    }
}
