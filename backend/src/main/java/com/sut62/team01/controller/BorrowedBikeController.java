package com.din.dinentity.controller;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.din.dinentity.entity.BikeType;
import com.din.dinentity.entity.BorrowedBike;
import com.din.dinentity.entity.Student;
import com.din.dinentity.entity.payload.BorrowedBikeRequest;
import com.din.dinentity.entity.payload.LoginRequest;
import com.din.dinentity.repository.BikeTypeRepository;
import com.din.dinentity.repository.BorrowedBikeRepository;
import com.din.dinentity.repository.StudentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * BorrowedBikeController
 */
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class BorrowedBikeController {

    @Autowired
    private BorrowedBikeRepository borrowedBikeRepository;

    @Autowired
    private BikeTypeRepository bikeTypeRepository;

    @Autowired
    private StudentRepository studentRepository;

    @GetMapping("/borrowedbikes")
    public Iterable<BorrowedBike> getBorrowedBikes() {
        return borrowedBikeRepository.findAll();
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

    @PostMapping("/myborrowedbike")
    public ResponseEntity<?> getMyBorrowedBike(@RequestBody LoginRequest loginRequest) {
        // Optional<Student> my =
        // studentRepository.findByUsernameAndPassword(loginRequest.getUsername(),
        // loginRequest.getPassword());
        Student my = studentRepository.findByUsernameAndPassword(loginRequest.getUsername(),
                loginRequest.getPassword());
        if (my == null) {
            return ResponseEntity.badRequest().body("Error: Username or Password is incorrect.");
        } else {
            List<BorrowedBike> bb = borrowedBikeRepository.findByStudent(my);
            if (bb.isEmpty()) {
                return ResponseEntity.badRequest().body("Error: You have no borrowed bike request.");
            } else {
                return ResponseEntity.ok().body(bb);
            }
        }
    }

    @PostMapping(value = "/borrowedbike")
    public ResponseEntity<?> newBorrowedBike(@RequestBody BorrowedBikeRequest bbRequest) {

        BorrowedBike _bb = new BorrowedBike();

        Optional<BikeType> bikeType = bikeTypeRepository.findById(bbRequest.getBikeType_id());
        if (bikeType.isPresent()) {
            BikeType _bikeType = bikeType.get();
            _bb.setBikeType(_bikeType);
        } else {
            return ResponseEntity.badRequest().body("Error: BikeType not found!");
        }

        Optional<Student> optionalStudent = studentRepository.findById(bbRequest.getStudent_id());
        if (optionalStudent.isPresent()) {
            Student student = optionalStudent.get();
            _bb.setStudent(student);
        } else {
            return ResponseEntity.badRequest().body("Error: Student not found");
        }
        _bb.setBorrowedDate(new Date());
        borrowedBikeRepository.save(_bb);
        return ResponseEntity.ok().body(_bb);

    }

}