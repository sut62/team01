package com.sut62.team01.controller;

import java.util.Collection;
import java.util.stream.Collectors;

import com.sut62.team01.entity.VehicleType;
import com.sut62.team01.repository.VehicleTypeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api")
public class VehicleTypeController {

    @Autowired // เป็นการ connect ไปยัง database ซึ่งในที่นี้เราไม่ต้องเขียน connection database เอง
    private VehicleTypeRepository vehicleTypeRepository;

    @GetMapping("/vehicleTypes")
    public Collection<VehicleType> getVehicleTypes() {
        return vehicleTypeRepository.findAll().stream().collect(Collectors.toList());
    }
}