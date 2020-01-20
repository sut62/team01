package com.sut62.team01.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.Collection;
import java.util.stream.Collectors;

import com.sut62.team01.entity.Bed;
import com.sut62.team01.repository.BedRepository;



@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api")
public class BedController {

    @Autowired
    BedRepository bedRepository;

    @GetMapping("/bed")
    public Collection<Bed> getAllBed() {
        return bedRepository.findAll().stream().collect(Collectors.toList());
    }

}