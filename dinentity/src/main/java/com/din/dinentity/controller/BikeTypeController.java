package com.din.dinentity.controller;

import com.din.dinentity.entity.BikeType;
import com.din.dinentity.repository.BikeTypeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * BikeTypeController
 */
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class BikeTypeController {

    @Autowired
    private BikeTypeRepository bikeTypeRepository;

    @GetMapping("/biketypes")
    public Iterable<BikeType> getAllBikeTypes(){
        return bikeTypeRepository.findAll();
    }
}