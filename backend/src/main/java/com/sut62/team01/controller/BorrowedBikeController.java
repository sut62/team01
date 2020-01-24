package com.sut62.team01.controller;

import java.util.Date;
import java.util.Optional;

import com.sut62.team01.entity.BikeType;
import com.sut62.team01.entity.BorrowedBike;
import com.sut62.team01.entity.DateType;
import com.sut62.team01.entity.RoomBooking;
import com.sut62.team01.entity.payload.BorrowedBikeRequest;
import com.sut62.team01.repository.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class BorrowedBikeController {

    @Autowired
    private BorrowedBikeRepository borrowedBikeRepository;

    @Autowired
    private BikeTypeRepository bikeTypeRepository;

    @Autowired
    private RoomBookingRepository roomBookingRepository;

    @Autowired
    private DateTypeRepository dateTypeRepository;

    @Autowired
    private BikeRepository bikeRepository;

    @GetMapping("/borrowedbikes")
    public Iterable<BorrowedBike> getBorrowedBikes() {
        return borrowedBikeRepository.findAll();
    }

    @GetMapping("/borrowedbikes/bike_null")
    public Iterable<BorrowedBike> getBorrowedBikes2() {
        return borrowedBikeRepository.findByBikeIsNull();
    }

    @GetMapping("/borrowedbike/{id}")
    public ResponseEntity<?> getSpecificBorrowedBike(@PathVariable Long id) {
        Optional<BorrowedBike> bb = borrowedBikeRepository.findById(id);
        if (bb.isPresent()) {
            return ResponseEntity.ok().body(bb.get());
        } else {
            return ResponseEntity.badRequest().body("Error: BorrowedBike_ID " + id + "Not found!");
        }

    }

    @PostMapping(value = "/borrowedbike")
    public ResponseEntity<?> newBorrowedBike(@RequestBody BorrowedBikeRequest bbRequest) {

        // 3.new BorowedBike
        BorrowedBike _bb = new BorrowedBike();

//        4.findById BikeType
        Optional<BikeType> bikeType = bikeTypeRepository.findById(bbRequest.getBikeType_id());
        if (!bikeType.isPresent()) {
            return ResponseEntity.badRequest().body("Error: BikeType not found!");
        }

//        5.findById RoomBooking
        Optional<RoomBooking> rb = roomBookingRepository.findById(bbRequest.getRoomBooking_id());
        if (!rb.isPresent()) {
            return ResponseEntity.badRequest().body("Error: RoomBooking not found");
        }

//        6.findById DateType
        Optional<DateType> _dt = dateTypeRepository.findById(bbRequest.getDateType_id());
        if (!_dt.isPresent()) {
            return ResponseEntity.badRequest().body("Error: DateType not found!");
        }

//        7.set BikeType
        _bb.setBikeType(bikeType.get());
//        8.set RoomBooking
        _bb.setRoomBooking(rb.get());
        // 9.set DateType
        _bb.setDateType(_dt.get());
        // 10.set RequestDate
        _bb.setRequestDate(new Date());

        // 11.save BorrowedBike
        borrowedBikeRepository.save(_bb);

        return ResponseEntity.ok().body(_bb);
    }

}