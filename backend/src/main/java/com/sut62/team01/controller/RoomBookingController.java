package com.sut62.team01.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParseException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.sut62.team01.entity.Branches;
import com.sut62.team01.entity.Rooms;
import com.sut62.team01.entity.RoomBooking;
import com.sut62.team01.entity.Students;
import com.sut62.team01.entity.payload.FindStudentPayload;
import com.sut62.team01.repository.BranchesRepository;
import com.sut62.team01.repository.RoomBookingRepository;
import com.sut62.team01.repository.RoomsRepository;
import com.sut62.team01.repository.StudentsRepository;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class RoomBookingController {
    @Autowired
    private RoomBookingRepository roomBookingRepository;
    @Autowired
    private RoomsRepository roomsrepository;
    @Autowired
    private StudentsRepository studentsrepository;
    @Autowired
    private BranchesRepository branchesrepository;

    @GetMapping("/roombooking")
    public Collection<RoomBooking> getAllRoomBookings() {
        return roomBookingRepository.findAll().stream().collect(Collectors.toList());
    }

    @PostMapping("/roombooking/{students_id}/{branches_id}/{rooms_id}")
    public RoomBooking newroombooking(RoomBooking newRoomBooking, @PathVariable long students_id,
            @PathVariable long branches_id, @PathVariable long rooms_id) {

        Students students = studentsrepository.findById(students_id);
        Branches branches = branchesrepository.findById(branches_id);
        Rooms rooms = roomsrepository.findById(rooms_id);

        newRoomBooking.setStudent(students);
        newRoomBooking.setBranches(branches);
        newRoomBooking.setRooms(rooms);

        return roomBookingRepository.save(newRoomBooking);

    }

    // TODO: BorrowedBikeUI ต้องใช้ => ค้นหานักศึกษาที่เจาะจง ใน RoomBooking;
    @GetMapping("/roombooking/student")
    public ResponseEntity<?> findRoomBookingWhereStudent(@RequestBody FindStudentPayload payload) {

        Optional<Students> student = studentsrepository.findById(payload.getStudent_id());
        if (student.isPresent()) {
            return ResponseEntity.ok().body(roomBookingRepository.findByStudent(student.get()));
        }
        

            return ResponseEntity.badRequest().body("Error: Incorrect Student_id!");
        
    }
}