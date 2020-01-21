package com.sut62.team01.controller;

import java.util.Collection;
import java.util.Optional;

import com.sut62.team01.entity.Bike;
import com.sut62.team01.entity.BikeType;
import com.sut62.team01.entity.payload.BikePayload;
import com.sut62.team01.repository.BikeRepository;
import com.sut62.team01.repository.BikeTypeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@CrossOrigin(origins = "*")
@RequestMapping("/api")
@RestController
public class BikeController {

    @Autowired
    private BikeRepository bikeRepository;

    @Autowired
    private BikeTypeRepository bikeTypeRepository;

    @PostMapping(value = "/bike")
    public ResponseEntity<?> getBike(@RequestBody BikePayload payload) {
        Optional<BikeType> bikeType = bikeTypeRepository.findById(payload.getBikeTypeId());
        if (!bikeType.isPresent())
            return ResponseEntity.badRequest().body("Error: BikeType not found!");
        Collection<Bike> bikes = bikeRepository.findByBikeType(bikeType.get());
        return ResponseEntity.ok().body(bikes);
    }

}