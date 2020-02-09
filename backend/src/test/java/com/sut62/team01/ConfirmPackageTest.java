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
public class ConfirmPackageTest {

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

    @Autowired
    private ConfirmPackageRepository confirmPackageRepository;

    @BeforeEach
    public void setup() {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }

    @Test
    void b6018474_testInsertDataOk() {
        ConfirmPackage confirmPackage = new ConfirmPackage();

        // necessary for roombooking
        Students students = new Students("Pontep Thaweesup", "B6000783", "วิศวกรรมศาสตร์", "pontep", "1234");
        students = studentsRepository.saveAndFlush(students);
        Rooms rooms = new Rooms("7133");
        rooms = roomsRepository.saveAndFlush(rooms);
        Bed bed = new Bed("What's branch?");
        bed = bedRepository.saveAndFlush(bed);
        RoomBooking roomBooking = new RoomBooking(students, rooms, bed, "pakorn@hotmail.com");
        roomBooking = roomBookingRepository.saveAndFlush(roomBooking);
        // necessary for PackageManagement
        PackageType packageType = new PackageType("sth");
        packageType = packageTypeRepository.saveAndFlush(packageType);
        Staff staff = new Staff("TestStaff", "test", "1234");
        staff = staffRepository.saveAndFlush(staff);
        PackageManagement packageManagement = new PackageManagement(roomBooking, packageType, staff, "details");
        packageManagement = packageManagementRepository.saveAndFlush(packageManagement);

        confirmPackage.setPackageManagement(packageManagement);
        confirmPackage.setConfirmDate(new Date());
        confirmPackage.setStaff(staff);

        confirmPackage = confirmPackageRepository.saveAndFlush(confirmPackage);

        Optional<ConfirmPackage> found = confirmPackageRepository.findById(confirmPackage.getId());
        assertEquals(confirmPackage, found.get());
    }

    @Test
    void b6018474_testPackagMustNotBeNull() {
        ConfirmPackage confirmPackage = new ConfirmPackage();

        // necessary for roombooking
        Students students = new Students("Pontep Thaweesup", "B6000783", "วิศวกรรมศาสตร์", "pontep", "1234");
        students = studentsRepository.saveAndFlush(students);
        Rooms rooms = new Rooms("7133");
        rooms = roomsRepository.saveAndFlush(rooms);
        Bed bed = new Bed("What's branch?");
        bed = bedRepository.saveAndFlush(bed);
        RoomBooking roomBooking = new RoomBooking(students, rooms, bed, "pakorn@hotmail.com");
        roomBooking = roomBookingRepository.saveAndFlush(roomBooking);
        // necessary for PackageManagement
        PackageType packageType = new PackageType("sth");
        packageType = packageTypeRepository.saveAndFlush(packageType);
        Staff staff = new Staff("TestStaff", "test", "1234");
        staff = staffRepository.saveAndFlush(staff);
        PackageManagement packageManagement = new PackageManagement(roomBooking, packageType, staff, "details");
        packageManagement = packageManagementRepository.saveAndFlush(packageManagement);

        confirmPackage.setPackageManagement(null);
        confirmPackage.setConfirmDate(new Date());
        confirmPackage.setStaff(staff);

        Set<ConstraintViolation<ConfirmPackage>> result = validator.validate(confirmPackage);

        assertEquals(1, result.size());
        assertEquals("must not be null", result.iterator().next().getMessage());
        assertEquals("packageManagement", result.iterator().next().getPropertyPath().toString());

    }

    @Test
    void b6018474_testConfirmDateMustNotBeNull() {
        ConfirmPackage confirmPackage = new ConfirmPackage();

        // necessary for roombooking
        Students students = new Students("Pontep Thaweesup", "B6000783", "วิศวกรรมศาสตร์", "pontep", "1234");
        students = studentsRepository.saveAndFlush(students);
        Rooms rooms = new Rooms("7133");
        rooms = roomsRepository.saveAndFlush(rooms);
        Bed bed = new Bed("What's branch?");
        bed = bedRepository.saveAndFlush(bed);
        RoomBooking roomBooking = new RoomBooking(students, rooms, bed, "pakorn@hotmail.com");
        roomBooking = roomBookingRepository.saveAndFlush(roomBooking);
        // necessary for PackageManagement
        PackageType packageType = new PackageType("sth");
        packageType = packageTypeRepository.saveAndFlush(packageType);
        Staff staff = new Staff("TestStaff", "test", "1234");
        staff = staffRepository.saveAndFlush(staff);
        PackageManagement packageManagement = new PackageManagement(roomBooking, packageType, staff, "details");
        packageManagement = packageManagementRepository.saveAndFlush(packageManagement);

        confirmPackage.setPackageManagement(packageManagement);
        confirmPackage.setConfirmDate(null);
        confirmPackage.setStaff(staff);

        Set<ConstraintViolation<ConfirmPackage>> result = validator.validate(confirmPackage);

        assertEquals(1, result.size());
        assertEquals("must not be null", result.iterator().next().getMessage());
        assertEquals("confirmDate", result.iterator().next().getPropertyPath().toString());

    }

    @Test
    void b6018474_testStaffMustNotBeNull() {
        ConfirmPackage confirmPackage = new ConfirmPackage();

        // necessary for roombooking
        Students students = new Students("Pontep Thaweesup", "B6000783", "วิศวกรรมศาสตร์", "pontep", "1234");
        students = studentsRepository.saveAndFlush(students);
        Rooms rooms = new Rooms("7133");
        rooms = roomsRepository.saveAndFlush(rooms);
        Bed bed = new Bed("What's branch?");
        bed = bedRepository.saveAndFlush(bed);
        RoomBooking roomBooking = new RoomBooking(students, rooms, bed, "pakorn@hotmail.com");
        roomBooking = roomBookingRepository.saveAndFlush(roomBooking);
        // necessary for PackageManagement
        PackageType packageType = new PackageType("sth");
        packageType = packageTypeRepository.saveAndFlush(packageType);
        Staff staff = new Staff("TestStaff", "test", "1234");
        staff = staffRepository.saveAndFlush(staff);
        PackageManagement packageManagement = new PackageManagement(roomBooking, packageType, staff, "details");
        packageManagement = packageManagementRepository.saveAndFlush(packageManagement);

        confirmPackage.setPackageManagement(packageManagement);
        confirmPackage.setConfirmDate(new Date());
        confirmPackage.setStaff(null);

        Set<ConstraintViolation<ConfirmPackage>> result = validator.validate(confirmPackage);

        assertEquals(1, result.size());
        assertEquals("must not be null", result.iterator().next().getMessage());
        assertEquals("staff", result.iterator().next().getPropertyPath().toString());

    }
}