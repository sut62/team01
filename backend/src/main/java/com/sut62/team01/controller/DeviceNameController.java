package com.sut62.team01.controller;

import com.sut62.team01.entity.DeviceName;
import com.sut62.team01.repository.DeviceNameRepository;
import com.sut62.team01.entity.DeviceType;
import com.sut62.team01.repository.DeviceTypeRepository;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.CrossOrigin;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("/api")
public class DeviceNameController {

        @Autowired
        DeviceNameRepository deviceNameRepository;

        @Autowired
        DeviceTypeRepository deviceTypeRepository;

        @GetMapping("/DeviceNames")
        public Collection<DeviceName> getNames() {
                return deviceNameRepository.findAll().stream().collect(Collectors.toList());

        }

        @GetMapping("/DeviceName/{id}")
        public List<DeviceName> Device(@PathVariable long id) {
                DeviceType deviceType = deviceTypeRepository.findById(id);
                List<DeviceName> deviceName = deviceNameRepository.findByDeviceType(deviceType);
                return deviceName;

        }



}