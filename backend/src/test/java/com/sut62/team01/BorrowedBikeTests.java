package com.sut62.team01;

import com.sut62.team01.entity.*;
import com.sut62.team01.repository.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Date;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class BorrowedBikeTests {

    private Validator validator;

    @Autowired
    private BorrowedBikeRepository borrowedBikeRepository;

    @Autowired
    private BikeTypeRepository bikeTypeRepository;

    @Autowired
    private DateTypeRepository dateTypeRepository;

    @Autowired
    private StudentsRepository studentsRepository;

    @Autowired
    private RoomBookingRepository roomBookingRepository;

    @Autowired
    private BedRepository bedRepository;

    @Autowired
    private RoomsRepository roomsRepository;

    @Autowired
    private BikeRepository bikeRepository;

    @Autowired
    private StaffRepository staffRepository;

    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void b6000783_testInsertDataToBorrowedBikeOK() {
        // จำลองข้อมูลที่จำเป็นในการบันทึก BorrowedBike
        BikeType bikeType = new BikeType("จักรยานล้อเดียว");
        bikeType = bikeTypeRepository.saveAndFlush(bikeType);
        DateType dateType = new DateType("ยืมแป๊ปเดียว");
        dateType = dateTypeRepository.saveAndFlush(dateType);
        Students students = new Students("Pontep Thaweesup", "B6000783","วิศวกรรมศาสตร์", "pontep", "1234");
        students = studentsRepository.saveAndFlush(students);
        Rooms rooms = new Rooms("7133");
        rooms = roomsRepository.saveAndFlush(rooms);
        Bed bed = new Bed("What's bed?");
        bed = bedRepository.saveAndFlush(bed);
        RoomBooking roomBooking = new RoomBooking(students, rooms, bed,"ต้องการเตียงเสริม" );
        roomBooking = roomBookingRepository.saveAndFlush(roomBooking);

        // สร้าง borrowedBike และ set ค่าต่างๆ
        BorrowedBike borrowedBike = new BorrowedBike();
        borrowedBike.setBikeType(bikeType);
        borrowedBike.setDateType(dateType);
        borrowedBike.setRoomBooking(roomBooking);
        borrowedBike.setRequestDate(new Date());
        // บันทึก borrowedBike
        borrowedBike = borrowedBikeRepository.saveAndFlush(borrowedBike);
        // เรียก borrowedBike ที่พึ่งบันทึกขึ้นมาดู
        Optional<BorrowedBike> found = borrowedBikeRepository.findById(borrowedBike.getId());
        // assert check
        assertEquals(borrowedBike, found.get());
    }

//    OK
    @Test
    void b6000783_testPutDataApproveBorrowedBikeOK() {
        // จำลองข้อมูลที่จำเป็นในการบันทึก BorrowedBike
        BikeType bikeType = new BikeType("จักรยานล้อเดียว");
        bikeType = bikeTypeRepository.saveAndFlush(bikeType);
        DateType dateType = new DateType("ยืมแป๊ปเดียว");
        dateType = dateTypeRepository.saveAndFlush(dateType);
        Students students = new Students("Pontep Thaweesup", "B6000783", "pontep", "1234");
        students = studentsRepository.saveAndFlush(students);
        Rooms rooms = new Rooms("7133");
        rooms = roomsRepository.saveAndFlush(rooms);
        Bed bed = new Bed("What's bed?");
        bed = bedRepository.saveAndFlush(bed);
        RoomBooking roomBooking = new RoomBooking(students, rooms, bed,"ต้องการเตียงเสริม" );
        roomBooking = roomBookingRepository.saveAndFlush(roomBooking);

//        necessary data for approveBorrowedBike (bike,staff)
        Bike bike = new Bike(bikeType,"Bike1234");
        bike = bikeRepository.saveAndFlush(bike);
        Staff staff = new Staff("TestStaff","test","1234");
        staff = staffRepository.saveAndFlush(staff);

        // สร้าง borrowedBike และ set ค่าต่างๆ
        BorrowedBike borrowedBike = new BorrowedBike();
        borrowedBike.setBikeType(bikeType);
        borrowedBike.setDateType(dateType);
        borrowedBike.setRoomBooking(roomBooking);
        borrowedBike.setRequestDate(new Date());
        // บันทึก borrowedBike
        borrowedBike = borrowedBikeRepository.saveAndFlush(borrowedBike);
        // เรียก borrowedBike ที่พึ่งบันทึกขึ้นมาดู
        Optional<BorrowedBike> found = borrowedBikeRepository.findById(borrowedBike.getId());
        // assert check
        assertEquals(borrowedBike, found.get());

//        Approve Borrowed Bike
        Optional<BorrowedBike> approved = borrowedBikeRepository.findById(borrowedBike.getId());
        Optional<Bike> selectedBike = bikeRepository.findById(bike.getId());
        Staff selectedStaff = staffRepository.findById(staff.getId());
        approved.get().setBike(selectedBike.get());
        approved.get().setStaff(selectedStaff);
        approved.equals(borrowedBikeRepository.saveAndFlush(approved.get()));

        Optional<BorrowedBike> foundApproved = borrowedBikeRepository.findById(approved.get().getId());
        // assert check
        assertEquals(borrowedBike, foundApproved.get());
    }

    @Test
    void b6000783_testRequestDateMustNotBeNull() {
        // จำลองข้อมูลที่จำเป็นในการบันทึก BorrowedBike
        BikeType bikeType = new BikeType("จักรยานล้อเดียว");
        bikeType = bikeTypeRepository.saveAndFlush(bikeType);
        DateType dateType = new DateType("ยืมแป๊ปเดียว");
        dateType = dateTypeRepository.saveAndFlush(dateType);
        Students students = new Students("Pontep Thaweesup", "B6000783","วิศวกรรมศาสตร์", "pontep", "1234");
        students = studentsRepository.saveAndFlush(students);
        Rooms rooms = new Rooms("7133");
        rooms = roomsRepository.saveAndFlush(rooms);
        Bed bed = new Bed("What's bed?");
        bed = bedRepository.saveAndFlush(bed);
        RoomBooking roomBooking = new RoomBooking(students, rooms, bed,"ต้องการเตียงเสริม");
        roomBooking = roomBookingRepository.saveAndFlush(roomBooking);

        // สร้าง borrowedBike และ set ค่าต่างๆ
        BorrowedBike borrowedBike = new BorrowedBike();
        borrowedBike.setBikeType(bikeType);
        borrowedBike.setDateType(dateType);
        borrowedBike.setRoomBooking(roomBooking);
        // ทดลองให้ set requestDate เป็น null
        borrowedBike.setRequestDate(null);
        // validate borrowedBike
        Set<ConstraintViolation<BorrowedBike>> result = validator.validate(borrowedBike);
        // ต้องมี 1 Error
        assertEquals(1, result.size());
        // error message ตรงชนิด และ ถูก field
        assertEquals("must not be null", result.iterator().next().getMessage());
        assertEquals("requestDate", result.iterator().next().getPropertyPath().toString());
    }

    @Test
    void b6000783_testDateTypeMustNotBeNull() {
        // จำลองข้อมูลที่จำเป็นในการบันทึก BorrowedBike
        BikeType bikeType = new BikeType("จักรยานล้อเดียว");
        bikeType = bikeTypeRepository.saveAndFlush(bikeType);
        DateType dateType = new DateType("ยืมแป๊ปเดียว");
        dateType = dateTypeRepository.saveAndFlush(dateType);
        Students students = new Students("Pontep Thaweesup", "B6000783","วิศวกรรมศาสตร์", "pontep", "1234");
        students = studentsRepository.saveAndFlush(students);
        Rooms rooms = new Rooms("7133");
        rooms = roomsRepository.saveAndFlush(rooms);
        Bed bed = new Bed("What's bed?");
        bed = bedRepository.saveAndFlush(bed);
        RoomBooking roomBooking = new RoomBooking(students, rooms, bed,"ต้องการเตียงเสริม");
        roomBooking = roomBookingRepository.saveAndFlush(roomBooking);

        // สร้าง borrowedBike และ set ค่าต่างๆ
        BorrowedBike borrowedBike = new BorrowedBike();
        borrowedBike.setBikeType(bikeType);
        borrowedBike.setDateType(null);
        borrowedBike.setRoomBooking(roomBooking);
        borrowedBike.setRequestDate(new Date());
        // validate borrowedBike
        Set<ConstraintViolation<BorrowedBike>> result = validator.validate(borrowedBike);
        // ต้องมี 1 Error
        assertEquals(1, result.size());
        // error message ตรงชนิด และ ถูก field
        assertEquals("must not be null", result.iterator().next().getMessage());
        assertEquals("dateType", result.iterator().next().getPropertyPath().toString());
    }

    @Test
    void b6000783_testBikeTypeMustNotBeNull() {
        // จำลองข้อมูลที่จำเป็นในการบันทึก BorrowedBike
        BikeType bikeType = new BikeType("จักรยานล้อเดียว");
        bikeType = bikeTypeRepository.saveAndFlush(bikeType);
        DateType dateType = new DateType("ยืมแป๊ปเดียว");
        dateType = dateTypeRepository.saveAndFlush(dateType);
        Students students = new Students("Pontep Thaweesup", "B6000783","วิศวกรรมศาสตร์", "pontep", "1234");
        students = studentsRepository.saveAndFlush(students);
        Rooms rooms = new Rooms("7133");
        rooms = roomsRepository.saveAndFlush(rooms);
        Bed bed = new Bed("What's bed?");
        bed = bedRepository.saveAndFlush(bed);
        RoomBooking roomBooking = new RoomBooking(students, rooms, bed,"ต้องการเตียงเสริม");
        roomBooking = roomBookingRepository.saveAndFlush(roomBooking);

        // สร้าง borrowedBike และ set ค่าต่างๆ
        BorrowedBike borrowedBike = new BorrowedBike();
        // ทดลองให้ set BikeType เป็น null
        borrowedBike.setBikeType(null);
        borrowedBike.setDateType(dateType);
        borrowedBike.setRoomBooking(roomBooking);
        borrowedBike.setRequestDate(new Date());
        // validate borrowedBike
        Set<ConstraintViolation<BorrowedBike>> result = validator.validate(borrowedBike);
        // ต้องมี 1 Error
        assertEquals(1, result.size());
        // error message ตรงชนิด และ ถูก field
        assertEquals("must not be null", result.iterator().next().getMessage());
        assertEquals("bikeType", result.iterator().next().getPropertyPath().toString());
    }

    @Test
    void b6000783_testRoomBookingMustNotBeNull() {
        // จำลองข้อมูลที่จำเป็นในการบันทึก BorrowedBike
        BikeType bikeType = new BikeType("จักรยานล้อเดียว");
        bikeType = bikeTypeRepository.saveAndFlush(bikeType);
        DateType dateType = new DateType("ยืมแป๊ปเดียว");
        dateType = dateTypeRepository.saveAndFlush(dateType);
        Students students = new Students("Pontep Thaweesup", "B6000783","วิศวกรรมศาสตร์", "pontep", "1234");
        students = studentsRepository.saveAndFlush(students);
        Rooms rooms = new Rooms("7133");
        rooms = roomsRepository.saveAndFlush(rooms);
        Bed bed = new Bed("What's bed?");
        bed = bedRepository.saveAndFlush(bed);
        RoomBooking roomBooking = new RoomBooking(students, rooms, bed,"ต้องการเตียงเสริม");
        roomBooking = roomBookingRepository.saveAndFlush(roomBooking);

        // สร้าง borrowedBike และ set ค่าต่างๆ
        BorrowedBike borrowedBike = new BorrowedBike();
        borrowedBike.setBikeType(bikeType);
        borrowedBike.setDateType(dateType);
        borrowedBike.setRequestDate(new Date());
        // ทดลองให้ set RoomBooking เป็น null
        borrowedBike.setRoomBooking(null);

        // validate borrowedBike
        Set<ConstraintViolation<BorrowedBike>> result = validator.validate(borrowedBike);
        // ต้องมี 1 Error
        assertEquals(1, result.size());
        // error message ตรงชนิด และ ถูก field
        assertEquals("must not be null", result.iterator().next().getMessage());
        assertEquals("roomBooking", result.iterator().next().getPropertyPath().toString());
    }
}
