package com.sut62.team01.controller;

import com.sut62.team01.entity.Bike;
import com.sut62.team01.entity.BorrowedBike;
import com.sut62.team01.entity.Staff;
import com.sut62.team01.entity.payload.ApproveBorrowedBikeRequest;
import com.sut62.team01.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
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
    public ResponseEntity<?> approveBorrowedBike(@RequestBody ApproveBorrowedBikeRequest request) {
        //3 findById BorrowedBike
        Optional<BorrowedBike> borrowedBike = borrowedBikeRepository.findById(request.getBorrowedBikeId());
        if (!borrowedBike.isPresent())
            return ResponseEntity.badRequest().body("Error: BorrowedBike not found!");

        //4 findById Bike
        Optional<Bike> bike = bikeRepository.findById(request.getBikeId());
        if (!bike.isPresent())
            return ResponseEntity.badRequest().body("Error: Bike not found!");

        //5 findById Staff
        Optional<Staff> staff = staffRepository.findById(request.getStaffId());
        if (!staff.isPresent())
            return ResponseEntity.badRequest().body("Error: Staff not found!");

        //6 BorrowedBike: set Bike
        borrowedBike.get().setBike(bike.get());

        //7 set Staff
        borrowedBike.get().setStaff(staff.get());

        //8 Bike: set Available status -> available = false
        bike.get().setAvailable(false);

//        9 set ApproveDate (now)
        borrowedBike.get().setApproveDate(new Date());
//        10 set Details
        borrowedBike.get().setDetails(request.getDetails());

        //11 BorrowedBike: save()
        return ResponseEntity.ok().body(borrowedBikeRepository.save(borrowedBike.get()));
    }

}
