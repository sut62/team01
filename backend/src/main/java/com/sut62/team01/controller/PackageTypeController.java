package com.sut62.team01.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.Collection;
import java.util.stream.Collectors;

import com.sut62.team01.entity.PackageType;
import com.sut62.team01.repository.PackageTypeRepository;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api")
public class PackageTypeController {

    @Autowired
    PackageTypeRepository packageTypeRepository;

    @GetMapping("/packageType")
    public Collection<PackageType> getAllPackageTypes() {
        return packageTypeRepository.findAll().stream().collect(Collectors.toList());
    }

}