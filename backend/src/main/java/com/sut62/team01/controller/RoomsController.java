package com.sut62.team01.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import antlr.collections.List;

import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.Collection;
import java.util.stream.Collectors;

import com.sut62.team01.entity.RoomBooking;
import com.sut62.team01.entity.Rooms;
import com.sut62.team01.repository.RoomBookingRepository;
import com.sut62.team01.repository.RoomsRepository;



@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api")
public class RoomsController {

    @Autowired
    RoomsRepository roomsRepository;

    @Autowired
    RoomBookingRepository roomBookingRepository;

    @GetMapping("/rooms")
    public Collection<Rooms> getAllRooms() {
        return roomsRepository.findAll().stream().collect(Collectors.toList());
    }
    
    @GetMapping("/GetAndSortData")
    public java.util.List<RoomBooking> getAllsort(){
        return roomBookingRepository.sortByRooms();
    }

    // @GetMapping("/a={rooms}")
    // public java.util.List<RoomBooking> getAll(Rooms rooms){
    //     return roomBookingRepository.sortByRooms(rooms);
    // }

}