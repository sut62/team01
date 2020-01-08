package com.sut62.team01.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.Collection;
import java.util.stream.Collectors;

import com.sut62.team01.entity.Students;
import com.sut62.team01.entity.payload.LoginRequest;
import com.sut62.team01.repository.StudentsRepository;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class StudentsController {

    @Autowired
    StudentsRepository studentsRepository;

    @GetMapping("/students")
    public Collection<Students> getAllStudents() {
        return studentsRepository.findAll().stream().collect(Collectors.toList());
    }

    // TODO: Login ค้นหานักเรียนที่มี username และ password ที่คาดหวัง
    @GetMapping("/student")
    public ResponseEntity<?> getStudentDetail(@RequestBody LoginRequest loginRequest) {
        Students my = studentsRepository.findByUsernameAndPassword(loginRequest.getUsername(),
                loginRequest.getPassword());
        if (my == null) {
            return ResponseEntity.badRequest().body("Error: Username or password is incorrect!");
        } else {
            return ResponseEntity.ok().body(my);
        }
    }

}