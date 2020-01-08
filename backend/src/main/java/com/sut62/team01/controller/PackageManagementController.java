package com.sut62.team01.controller;

import java.util.Collection;
import java.util.Date;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sut62.team01.entity.PackageManagement;
import com.sut62.team01.entity.RoomBooking;
import com.sut62.team01.entity.PackageType;
import com.sut62.team01.entity.Staff;
import com.sut62.team01.entity.payload.PackageManagementPayload;
import com.sut62.team01.repository.PackageManagementRepository;
import com.sut62.team01.repository.RoomBookingRepository;
import com.sut62.team01.repository.PackageTypeRepository;
import com.sut62.team01.repository.StaffRepository;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api")
public class PackageManagementController {
    @Autowired
    private PackageManagementRepository packageManagementRepository;
    @Autowired
    private RoomBookingRepository roomBookingRepository;
    @Autowired
    private PackageTypeRepository packageTypeRepository;
    @Autowired
    private StaffRepository staffRepository;

    @GetMapping("/PackageManagement")
    public Collection<PackageManagement> getAllPackageManagements() {
        return packageManagementRepository.findAll().stream().collect(Collectors.toList());
    }

    @PostMapping("/packageManagement/{ROOMBOOKING_ID}/{ELECTRICTYPE_ID}/{STAFF_ID}")
    public PackageManagement Save(PackageManagement newPackageManagement, @PathVariable long ROOMBOOKING_ID,
            @PathVariable long packageType_id, @PathVariable long STAFF_ID) {

        RoomBooking roomBooking = roomBookingRepository.findById(ROOMBOOKING_ID);
        PackageType packageType = packageTypeRepository.findById(packageType_id);
        Staff staff = staffRepository.findById(STAFF_ID);

        newPackageManagement.setPackageDate(new Date());
        newPackageManagement.setRoomBooking(roomBooking);
        newPackageManagement.setPackageType(packageType);
        newPackageManagement.setStaff(staff);

        return packageManagementRepository.save(newPackageManagement);
    }

    @PostMapping("/packageManagement")
    public PackageManagement newPackageManagement(@RequestBody PackageManagementPayload packages) {
        PackageManagement packageManagement = new PackageManagement();

        Optional<Staff> staff = staffRepository.findById(packages.getStaffId());
        Optional<RoomBooking> roomBooking = roomBookingRepository.findById(packages.getRoomBookingId());
        Optional<PackageType> packageType = packageTypeRepository.findById(packages.getPackageTypeId());

        packageManagement.setPackageDate(new Date());
        packageManagement.setStaff(staff.get());
        packageManagement.setRoomBooking(roomBooking.get());
        packageManagement.setPackageType(packageType.get());
        packageManagement.setDetails(packages.getDetails());
        return packageManagementRepository.save(packageManagement);
    }
}