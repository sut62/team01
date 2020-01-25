package com.sut62.team01.controller;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.sut62.team01.entity.EnrollVehicle;
import com.sut62.team01.entity.RoomBooking;
import com.sut62.team01.entity.Staff;
import com.sut62.team01.entity.VehicleType;
import com.sut62.team01.entity.payload.newEnrollVehiclePayload;
import com.sut62.team01.repository.EnrollVehicleRepository;
import com.sut62.team01.repository.RoomBookingRepository;
import com.sut62.team01.repository.StaffRepository;
import com.sut62.team01.repository.VehicleTypeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api")
public class EnrollVehicleController {

    @Autowired // เป็นการ connect ไปยัง database ซึ่งในที่นี้เราไม่ต้องเขียน connection database เอง
    private EnrollVehicleRepository enrollVehicleRepository;

    @Autowired
    private StaffRepository staffRepository;
    
    @Autowired
    private VehicleTypeRepository vehicleTypeRepository;
    
    @Autowired
    private RoomBookingRepository roomBookingRepository;

    @GetMapping("/enrolledVehicles")
    public Collection<EnrollVehicle> getEnrollVehicles() {
        return enrollVehicleRepository.findAll().stream().collect(Collectors.toList());
    }

    // ค้นหาจาก ป้ายทะเบียน
    // @GetMapping("/enrolledVehicles/searchLicensePlate={licensePlate}")
    // List<EnrollVehicle> findDetialsByLicensePlate(@PathVariable String licensePlate) {
    //     List<EnrollVehicle> en_V = enrollVehicleRepository.findByLicensePlate(licensePlate);
    //     return en_V;
    // }

    @GetMapping("/enrolledVehicles/searchBrandName={brandName}")
    List<EnrollVehicle> findDetialsByBrandName(@PathVariable String brandName) {
        List<EnrollVehicle> en_V = enrollVehicleRepository.findByBrandName(brandName);
        return en_V;
    }

    @PostMapping("/enrollVehicle")
    public EnrollVehicle newEnrollVehicle(@RequestBody newEnrollVehiclePayload newEnroll) {

        // new EnrollVehicle
        EnrollVehicle enrollVehicle = new EnrollVehicle();

        // ประกาศตัวแปรเพื่อเก็บ data ของ id
        Optional<VehicleType> vehicleType = vehicleTypeRepository.findById(newEnroll.getVehicleTypeId());
        Optional<Staff> staff = staffRepository.findById(newEnroll.getStaffId());
        Optional<RoomBooking> roomBooking = roomBookingRepository.findById(newEnroll.getRoomBookingId());

        // set Data
        enrollVehicle.setEnrollDate(new Date());
        enrollVehicle.setLicensePlate(newEnroll.getLicensePlate());
        enrollVehicle.setBrandName(newEnroll.getBrandName());
        enrollVehicle.setOtherDetails(newEnroll.getOtherDetails());
        enrollVehicle.setTypeOfVehicle(vehicleType.get());
        enrollVehicle.setCreatedBy(staff.get());
        enrollVehicle.setEnrolledStudents(roomBooking.get());

        // บันทึกข้อมูล
        return enrollVehicleRepository.save(enrollVehicle);
    }
}
