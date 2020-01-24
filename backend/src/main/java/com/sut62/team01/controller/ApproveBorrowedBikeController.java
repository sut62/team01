package com.sut62.team01.controller;

import com.sut62.team01.entity.Bike;
import com.sut62.team01.entity.BorrowedBike;
import com.sut62.team01.entity.Staff;
import com.sut62.team01.entity.payload.ApproveBorrowedBikeRequest;
import com.sut62.team01.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class ApproveBorrowedBikeController {
    @Autowired
    private BorrowedBikeRepository borrowedBikeRepository;

    @Autowired
    private BikeRepository bikeRepository;

    @Autowired
    private StaffRepository staffRepository;

    @PutMapping("/staff/borrowedbikerequest")
    public ResponseEntity<?> approveBorrowedBikeRequest(@RequestBody ApproveBorrowedBikeRequest request) {
        // find BorrowedBike
        Optional<BorrowedBike> borrowedBike = borrowedBikeRepository.findById(request.getBorrowedBikeId());
        if (!borrowedBike.isPresent())
            return ResponseEntity.badRequest().body("Error: BorrowedBike not found!");

        // find Bike
        Optional<Bike> bike = bikeRepository.findById(request.getBikeId());
        if (!bike.isPresent())
            return ResponseEntity.badRequest().body("Error: Bike not found!");

        // find Staff
        Optional<Staff> staff = staffRepository.findById(request.getStaffId());
        if (!staff.isPresent())
            return ResponseEntity.badRequest().body("Error: Staff not found!");

        // Bike: set Bike status -> available = false
        bike.get().setAvailable(false);
        // BorrowedBike: set Bike
        borrowedBike.get().setBike(bike.get());
        // set Staff
        borrowedBike.get().setStaff(staff.get());

        // OK. Return BorrowedBike object
        return ResponseEntity.ok().body(borrowedBikeRepository.save(borrowedBike.get()));
    }

}
