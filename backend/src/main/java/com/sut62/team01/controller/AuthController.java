package com.sut62.team01.controller;

import com.sut62.team01.entity.Staff;
import com.sut62.team01.entity.Students;
// import com.sut62.team01.entity.Student;
import com.sut62.team01.entity.payload.LoginRequest;
import com.sut62.team01.repository.StaffRepository;
// import com.sut62.team01.repository.StudentRepository;
import com.sut62.team01.repository.StudentsRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * AuthController
 */
@CrossOrigin(origins = "*")
@RequestMapping("/auth")
@RestController
public class AuthController {

    @Autowired
    private StudentsRepository studentRepository;

    @Autowired
    private StaffRepository staffRepository;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        Students student = studentRepository.findByUsernameAndPassword(loginRequest.getUsername(),
                loginRequest.getPassword());
        if (student == null) {
            return ResponseEntity.badRequest().body("Error: Username or Password is in correct.");
        }
        return ResponseEntity.ok().body(student);
    }

    @PostMapping("/staff/signin")
    public ResponseEntity<?> staffSigin(@RequestBody LoginRequest loginRequest) {
        Staff staff = staffRepository.findByUsernameAndPassword(loginRequest.getUsername(), loginRequest.getPassword());
        if (staff == null) {
            return ResponseEntity.badRequest().body("Error: Username or Password is in correct.");
        }
        return ResponseEntity.ok().body(staff);
    }
}