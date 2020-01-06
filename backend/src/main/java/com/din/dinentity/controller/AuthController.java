package com.din.dinentity.controller;

import com.din.dinentity.entity.Student;
import com.din.dinentity.entity.payload.LoginRequest;
import com.din.dinentity.repository.StudentRepository;

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
    private StudentRepository studentRepository;
    
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest){
        Student student = studentRepository.findByUsernameAndPassword(loginRequest.getUsername(), loginRequest.getPassword());
        if(student == null){
            return ResponseEntity.badRequest().body("Error: Username or Password is in correct.");
        }
        return ResponseEntity.ok().body(student);
    }
}