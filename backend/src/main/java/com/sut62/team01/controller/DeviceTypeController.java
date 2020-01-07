package com.sut62.team01.controller;

import com.sut62.team01.entity.DeviceType;
import com.sut62.team01.repository.DeviceTypeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.CrossOrigin;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("/api")
public class DeviceTypeController {

    @Autowired
    DeviceTypeRepository deviceTypeRepository;

    @GetMapping("/DeviceTypes")
    public Collection<DeviceType> getTypes() {
        return deviceTypeRepository.findAll().stream().collect(Collectors.toList());

    }

}