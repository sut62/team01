package com.din.dinentity.controller;

import com.din.dinentity.entity.Student;
import com.din.dinentity.entity.payload.LoginRequest;
import com.din.dinentity.repository.StudentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;


/**
 * StudentController
 */
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;

    @GetMapping("/student")
    public ResponseEntity<?> getStudentDetail(@RequestBody LoginRequest loginRequest) {
        Student my = studentRepository.findByUsernameAndPassword(loginRequest.getUsername(), loginRequest.getPassword());
        if(my == null){
            return ResponseEntity.badRequest().body("Error: Username or password is incorrect!");
        }else{
            return ResponseEntity.ok().body(my);
        }
    }
    
}