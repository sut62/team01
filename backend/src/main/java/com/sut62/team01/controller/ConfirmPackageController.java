package com.sut62.team01.controller;

import java.util.Collection;
import java.util.Date;
import java.util.Optional;
import java.util.stream.Collectors;

import com.sut62.team01.entity.ConfirmPackage;
import com.sut62.team01.entity.PackageManagement;
import com.sut62.team01.entity.Staff;
import com.sut62.team01.entity.payload.ConfirmPackagePayload;
import com.sut62.team01.repository.ConfirmPackageRepository;
import com.sut62.team01.repository.PackageManagementRepository;
import com.sut62.team01.repository.StaffRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api")
public class ConfirmPackageController {

    @Autowired // เป็นการ connect ไปยัง database ซึ่งในที่นี้เราไม่ต้องเขียน connection
               // database เอง
    private ConfirmPackageRepository confirmPackageRepository;
    @Autowired
    private PackageManagementRepository packageManagementRepository;

    @Autowired
    private StaffRepository staffRepository;

    @GetMapping("/confirmPackage")
    public Collection<ConfirmPackage> getAllConfirmPackages() {
        return confirmPackageRepository.findAll().stream().collect(Collectors.toList());
    }

    @PostMapping("/confirmPackage2")
    public ConfirmPackage newConfirmPackage2(ConfirmPackage newConfirmPackage,
            @RequestBody ConfirmPackagePayload payload) {

        Optional<PackageManagement> packageManagement = packageManagementRepository
                .findById(payload.getPackageManagementId());
        Optional<Staff> staff = staffRepository.findById(payload.getStaffId());
        newConfirmPackage.setPackageManagement(packageManagement.get());
        newConfirmPackage.setConfirmDate(new Date());
        newConfirmPackage.setStaff(staff.get());

        return confirmPackageRepository.save(newConfirmPackage);
    }

}