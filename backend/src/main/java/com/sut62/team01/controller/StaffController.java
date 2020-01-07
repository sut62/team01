package com.sut62.team01.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.Collection;
import java.util.stream.Collectors;

import com.sut62.team01.entity.Staff;
import com.sut62.team01.repository.StaffRepository;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api")
public class StaffController {

    @Autowired
    StaffRepository staffRepository;

    @GetMapping("/staff")
    public Collection<Staff> getAllsStaffs() {
        return staffRepository.findAll().stream().collect(Collectors.toList());
    }
    @GetMapping("/staff/{username}/{password}")
    public Staff employeeLogin(@PathVariable String username, @PathVariable String password){
        return staffRepository.findByUsernameAndPassword(username, password);
    }
    @GetMapping("/staff/{id}")
    public Staff employeeId(@PathVariable long id){
        return staffRepository.findById(id);
    }

}