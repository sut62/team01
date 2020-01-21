package com.sut62.team01;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Date;
import java.util.Optional;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import com.sut62.team01.entity.*;
import com.sut62.team01.repository.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

/**
 * PackageManagement
 */
@DataJpaTest
public class PackageManagementTest {

    private Validator validator;

    @Autowired
    private StudentsRepository studentsRepository;

    @Autowired
    private RoomBookingRepository roomBookingRepository;

    @Autowired
    private BedRepository bedRepository;

    @Autowired
    private RoomsRepository roomsRepository;

    @Autowired
    private PackageTypeRepository packageTypeRepository;

    @Autowired
    private StaffRepository staffRepository;

    @Autowired
    private PackageManagementRepository packageManagementRepository;

    @BeforeEach
    public void setup() {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }

    @Test
    void b6018474_testInsertDataOk() {
        // create oj
        PackageManagement packageManagement = new PackageManagement();
        // necessary for roombooking
        Students students = new Students("Pontep Thaweesup", "B6000783", "pontep", "1234");
        students = studentsRepository.saveAndFlush(students);
        Rooms rooms = new Rooms("7133");
        rooms = roomsRepository.saveAndFlush(rooms);
        Bed bed = new Bed("What's branch?");
        bed = bedRepository.saveAndFlush(bed);
        RoomBooking roomBooking = new RoomBooking(students, rooms, bed);
        roomBooking = roomBookingRepository.saveAndFlush(roomBooking);
        // necessary for PackageManagement
        PackageType packageType = new PackageType("sth");
        packageType = packageTypeRepository.saveAndFlush(packageType);
        Staff staff = new Staff("zxc", "zxcv", "zxcvb");
        staff = staffRepository.saveAndFlush(staff);

        packageManagement.setRoomBooking(roomBooking);
        packageManagement.setPackageType(packageType);
        packageManagement.setStaff(staff);
        packageManagement.setDetails("details");
        packageManagement.setPackageDate(new Date());
        packageManagement = packageManagementRepository.saveAndFlush(packageManagement);

        Optional<PackageManagement> found = packageManagementRepository.findById(packageManagement.getId());
        assertEquals(packageManagement, found.get());
    }

    @Test
    void b6018474_testStaffMustNotBeNull() {
        // create oj
        PackageManagement packageManagement = new PackageManagement();
        // necessary for roombooking
        Students students = new Students("Pontep Thaweesup", "B6000783", "pontep", "1234");
        students = studentsRepository.saveAndFlush(students);
        Rooms rooms = new Rooms("7133");
        rooms = roomsRepository.saveAndFlush(rooms);
        Bed bed = new Bed("What's branch?");
        bed = bedRepository.saveAndFlush(bed);
        RoomBooking roomBooking = new RoomBooking(students, rooms, bed);
        roomBooking = roomBookingRepository.saveAndFlush(roomBooking);
        // necessary for PackageManagement
        PackageType packageType = new PackageType("sth");
        packageType = packageTypeRepository.saveAndFlush(packageType);
        Staff staff = new Staff("zxc", "zxcv", "zxcvb");
        staff = staffRepository.saveAndFlush(staff);

        packageManagement.setRoomBooking(roomBooking);
        packageManagement.setPackageType(packageType);
        packageManagement.setStaff(null);
        packageManagement.setDetails("details");
        packageManagement.setPackageDate(new Date());

        Set<ConstraintViolation<PackageManagement>> result = validator.validate(packageManagement);

        assertEquals(1, result.size());
        assertEquals("must not be null", result.iterator().next().getMessage());
        assertEquals("staff", result.iterator().next().getPropertyPath().toString());

    }

    @Test
    void b6018474_testPackageTypeMustNotBeNull() {

        // create oj
        PackageManagement packageManagement = new PackageManagement();
        // necessary for roombooking
        Students students = new Students("Pontep Thaweesup", "B6000783", "pontep", "1234");
        students = studentsRepository.saveAndFlush(students);
        Rooms rooms = new Rooms("7133");
        rooms = roomsRepository.saveAndFlush(rooms);
        Bed bed = new Bed("What's branch?");
        bed = bedRepository.saveAndFlush(bed);
        RoomBooking roomBooking = new RoomBooking(students, rooms, bed);
        roomBooking = roomBookingRepository.saveAndFlush(roomBooking);
        // necessary for PackageManagement
        PackageType packageType = new PackageType("asd");
        packageType = packageTypeRepository.saveAndFlush(packageType);
        Staff staff = new Staff("asdsa", "asdsa", "qwe");
        staff = staffRepository.saveAndFlush(staff);

        packageManagement.setRoomBooking(roomBooking);
        packageManagement.setPackageType(null);
        packageManagement.setStaff(staff);
        packageManagement.setDetails("details");
        packageManagement.setPackageDate(new Date());

        Set<ConstraintViolation<PackageManagement>> result = validator.validate(packageManagement);

        assertEquals(1, result.size());
        assertEquals("must not be null", result.iterator().next().getMessage());
        assertEquals("packageType", result.iterator().next().getPropertyPath().toString());

    }

    @Test
    void b6018474_testRoomBookingMustNotBeNull() {

        // create oj
        PackageManagement packageManagement = new PackageManagement();
        // necessary for roombooking
        Students students = new Students("Pontep Thaweesup", "B6000783", "pontep", "1234");
        students = studentsRepository.saveAndFlush(students);
        Rooms rooms = new Rooms("7133");
        rooms = roomsRepository.saveAndFlush(rooms);
        Bed bed = new Bed("What's bed?");
        bed = bedRepository.saveAndFlush(bed);
        RoomBooking roomBooking = new RoomBooking(students, rooms, bed);
        roomBooking = roomBookingRepository.saveAndFlush(roomBooking);
        // necessary for PackageManagement
        PackageType packageType = new PackageType("asd");
        packageType = packageTypeRepository.saveAndFlush(packageType);
        Staff staff = new Staff("asdsa", "asdsa", "qwe");
        staff = staffRepository.saveAndFlush(staff);

        packageManagement.setRoomBooking(null);
        packageManagement.setPackageType(packageType);
        packageManagement.setStaff(staff);
        packageManagement.setDetails("details");
        packageManagement.setPackageDate(new Date());

        Set<ConstraintViolation<PackageManagement>> result = validator.validate(packageManagement);

        assertEquals(1, result.size());
        assertEquals("must not be null", result.iterator().next().getMessage());
        assertEquals("roomBooking", result.iterator().next().getPropertyPath().toString());

    }
    
    @Test
    void b6018474_testDetailsNotLessThanMin() {

        // create oj
        PackageManagement packageManagement = new PackageManagement();
        // necessary for roombooking
        Students students = new Students("Pontep Thaweesup", "B6000783", "pontep", "1234");
        students = studentsRepository.saveAndFlush(students);
        Rooms rooms = new Rooms("7133");
        rooms = roomsRepository.saveAndFlush(rooms);
        Bed bed = new Bed("What's bed?");
        bed = bedRepository.saveAndFlush(bed);
        RoomBooking roomBooking = new RoomBooking(students, rooms, bed);
        roomBooking = roomBookingRepository.saveAndFlush(roomBooking);
        // necessary for PackageManagement
        PackageType packageType = new PackageType("asd");
        packageType = packageTypeRepository.saveAndFlush(packageType);
        Staff staff = new Staff("asdsa", "asdsa", "qwe");
        staff = staffRepository.saveAndFlush(staff);

        packageManagement.setRoomBooking(roomBooking);
        packageManagement.setPackageType(packageType);
        packageManagement.setStaff(staff);
        packageManagement.setDetails("1234");
        packageManagement.setPackageDate(new Date());

        Set<ConstraintViolation<PackageManagement>> result = validator.validate(packageManagement);

        assertEquals(1, result.size());
        assertEquals("size must be between 5 and 20", result.iterator().next().getMessage());
        assertEquals("details", result.iterator().next().getPropertyPath().toString());

    }

    @Test
    void b6018474_testDetailsNotGreaterThanMax() {

        // create oj
        PackageManagement packageManagement = new PackageManagement();
        // necessary for roombooking
        Students students = new Students("Pontep Thaweesup", "B6000783", "pontep", "1234");
        students = studentsRepository.saveAndFlush(students);
        Rooms rooms = new Rooms("7133");
        rooms = roomsRepository.saveAndFlush(rooms);
        Bed bed = new Bed("What's bed?");
        bed = bedRepository.saveAndFlush(bed);
        RoomBooking roomBooking = new RoomBooking(students, rooms, bed);
        roomBooking = roomBookingRepository.saveAndFlush(roomBooking);
        // necessary for PackageManagement
        PackageType packageType = new PackageType("asd");
        packageType = packageTypeRepository.saveAndFlush(packageType);
        Staff staff = new Staff("asdsa", "asdsa", "qwe");
        staff = staffRepository.saveAndFlush(staff);

        packageManagement.setRoomBooking(roomBooking);
        packageManagement.setPackageType(packageType);
        packageManagement.setStaff(staff);
        packageManagement.setDetails("123456789012345678901");
        packageManagement.setPackageDate(new Date());

        Set<ConstraintViolation<PackageManagement>> result = validator.validate(packageManagement);

        assertEquals(1, result.size());
        assertEquals("size must be between 5 and 20", result.iterator().next().getMessage());
        assertEquals("details", result.iterator().next().getPropertyPath().toString());

    }

}