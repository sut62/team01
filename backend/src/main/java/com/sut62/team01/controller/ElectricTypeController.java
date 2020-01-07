package com.sut62.team01.controller;

import java.util.Collection;
import java.util.stream.Collectors;

import com.sut62.team01.entity.ElectricType;
import com.sut62.team01.repository.ElectricTypeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class ElectricTypeController{
    @Autowired
    ElectricTypeRepository electrictypeRepository;
    
    @GetMapping("/electrictypes")
    public Collection<ElectricType> getAllElectrictypes(){
        return electrictypeRepository.findAll().stream().collect(Collectors.toList());
    }
}