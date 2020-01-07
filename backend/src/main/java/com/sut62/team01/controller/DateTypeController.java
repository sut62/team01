package com.sut62.team01.controller;

import com.sut62.team01.entity.BikeType;
import com.sut62.team01.entity.DateType;
import com.sut62.team01.repository.BikeTypeRepository;
import com.sut62.team01.repository.DateTypeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * BikeTypeController
 */
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class DateTypeController {

    @Autowired
    private DateTypeRepository dateTypeRepository;

    @GetMapping("/datetypes")
    public Iterable<DateType> getAllDateTypes() {
        return dateTypeRepository.findAll();
    }
}