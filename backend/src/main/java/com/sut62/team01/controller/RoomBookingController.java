package com.sut62.team01.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.sut62.team01.entity.Bed;
import com.sut62.team01.entity.Rooms;
import com.sut62.team01.entity.RoomBooking;
import com.sut62.team01.entity.Students;
import com.sut62.team01.entity.payload.FindStudentPayload;
import com.sut62.team01.entity.payload.RoomBookingPayload;
import com.sut62.team01.repository.BedRepository;
import com.sut62.team01.repository.RoomBookingRepository;
import com.sut62.team01.repository.RoomsRepository;
import com.sut62.team01.repository.StudentsRepository;

@CrossOrigin(origins = "http://localhost:8080")
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
    private BedRepository bedrepository;

    @GetMapping("/roombooking")
    public Collection<RoomBooking> getAllRoomBookings() {
        return roomBookingRepository.findAll().stream().collect(Collectors.toList());
    }

    // @PostMapping("/roombooking/{students_id}/{rooms_id}/{bed_id}")
    // public RoomBooking newroombooking(RoomBooking newRoomBooking, @PathVariable long students_id,
    //         @PathVariable long bed_id, @PathVariable long rooms_id, @PathVariable String detail) {

    //     Students students = studentsrepository.findById(students_id);
    //     Bed bed = bedrepository.findById(bed_id);
    //     Rooms rooms = roomsrepository.findById(rooms_id);

    //     newRoomBooking.setStudent(students);
    //     newRoomBooking.setBed(bed);
    //     newRoomBooking.setRooms(rooms);
    //     return roomBookingRepository.save(newRoomBooking);

    // }

    @PostMapping("/roombooking/new")
    public RoomBooking newRoomBooking2(@RequestBody RoomBookingPayload r) {
        RoomBooking newRoomBooking = new RoomBooking();
        Optional<Students> students = studentsrepository.findById(r.getStudent_id());
        Optional<Bed> bed = bedrepository.findById(r.getBed_id());
        Optional<Rooms> rooms = roomsrepository.findById(r.getRoom_id());

        newRoomBooking.setRoombookingDate(new Date());
        newRoomBooking.setStudent(students.get());
        newRoomBooking.setBed(bed.get());
        newRoomBooking.setRooms(rooms.get());
        newRoomBooking.setEmail(r.getEmail());
        return roomBookingRepository.save(newRoomBooking);
    }

    // TODO: BorrowedBikeUI ต้องใช้ => ค้นหานักศึกษาที่เจาะจง ใน RoomBooking;
    @PostMapping("/roombooking/student")
    public ResponseEntity<?> findRoomBookingWhereStudent(@RequestBody FindStudentPayload payload) {

        Optional<Students> student = studentsrepository.findById(payload.getStudent_id());
        if (student.isPresent()) {
            return ResponseEntity.ok().body(roomBookingRepository.findByStudent(student.get()));
        }

        return ResponseEntity.badRequest().body("Error: Incorrect Student_id!");

    }

    // ค้นหาห้องด้วย ชื่อห้อง (7133)
    @GetMapping("/roombooking/{roomName}")
    List<RoomBooking> findRoomNo(@PathVariable String roomName) {
        Rooms room = roomsrepository.findByRoomId(roomName);
        return roomBookingRepository.findByRooms(room);
    }

    // ค้นหาห้องด้วย ไอดีห้อง (33)
    @GetMapping("/roombooking/fronk/{x}")
    List<RoomBooking> findRoomNo2(@PathVariable Long x) {
        Optional<Rooms> room = roomsrepository.findById(x);
        return roomBookingRepository.findByRooms(room.get());
    }

    // @GetMapping("/findroomby={roomId}")
    // List<Rooms> searchRoomname(@PathVariable String roomId) {
    //     Optional<Rooms> room = roomsrepository.findByRoomId(roomId);
    //     return roomBookingRepository.findByRooms(room.get());
    // }
}