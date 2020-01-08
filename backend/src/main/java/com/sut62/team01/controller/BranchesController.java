package com.sut62.team01.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.Collection;
import java.util.stream.Collectors;

import com.sut62.team01.entity.Branches;
import com.sut62.team01.repository.BranchesRepository;



@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class BranchesController {

    @Autowired
    BranchesRepository branchesRepository;

    @GetMapping("/branches")
    public Collection<Branches> getAllBranches() {
        return branchesRepository.findAll().stream().collect(Collectors.toList());
    }

}