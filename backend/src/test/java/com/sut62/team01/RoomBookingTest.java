package com.sut62.team01;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
public class RoomBookingTest {

    private Validator validator;

    @Autowired
    private StudentsRepository studentsRepository;

    @Autowired
    private RoomBookingRepository roomBookingRepository;

    @Autowired
    private BedRepository bedRepository;

    @Autowired
    private RoomsRepository roomsRepository;

    @BeforeEach
    public void setup() {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }

    @Test
    void b6010201_testInsertDataOk() {
        // create oj
        RoomBooking roomBooking = new RoomBooking();
        // necessary for roombooking
        Students students = new Students("Pontep Thaweesup", "B6000783", "pontep", "1234");
        students = studentsRepository.saveAndFlush(students);
        Rooms rooms = new Rooms("7133");
        rooms = roomsRepository.saveAndFlush(rooms);
        Bed bed = new Bed("What's branch?");
        bed = bedRepository.saveAndFlush(bed);

        // necessary for PackageManagement
        roomBooking.setStudent(students);
        roomBooking.setRooms(rooms);
        roomBooking.setBed(bed);
        roomBooking.setDetail("ต้องการเตียงเสริม");
        roomBooking = roomBookingRepository.saveAndFlush(roomBooking);

        Optional<RoomBooking> found = roomBookingRepository.findById(roomBooking.getId());
        assertEquals(roomBooking, found.get());
    }

    @Test
    void b6010201_testStudentsMustNotBeNull() {
         // create oj
         // necessary for roombooking
         Students students = new Students("Pontep Thaweesup", "B6000783", "pontep", "1234");
         students = studentsRepository.saveAndFlush(students);
         Rooms rooms = new Rooms("7133");
         rooms = roomsRepository.saveAndFlush(rooms);
         Bed bed = new Bed("What's branch?");
         bed = bedRepository.saveAndFlush(bed);
 
        

         RoomBooking roomBooking = new RoomBooking(null,rooms,bed,"ต้องการเตียงเสริม");
        // necessary for PackageManagement
        // roomBooking.setStudent(students);
        // roomBooking.setRooms(rooms);
        // roomBooking.setBed(bed);
        // roomBooking.setDetail(null);
        // roomBooking = roomBookingRepository.saveAndFlush(roomBooking);


        Set<ConstraintViolation<RoomBooking>> result = validator.validate(roomBooking);

        assertEquals(1, result.size());
        assertEquals("must not be null", result.iterator().next().getMessage());
        assertEquals("student", result.iterator().next().getPropertyPath().toString());

    }

    @Test
    void b6010201_testRoomsMustNotBeNull() {
        Students students = new Students("Pontep Thaweesup", "B6000783", "pontep", "1234");
        students = studentsRepository.saveAndFlush(students);
        Rooms rooms = new Rooms("7133");
        rooms = roomsRepository.saveAndFlush(rooms);
        Bed bed = new Bed("What's branch?");
        bed = bedRepository.saveAndFlush(bed);

        RoomBooking roomBooking = new RoomBooking(students,null,bed,"ต้องการเตียงเสริม");

       Set<ConstraintViolation<RoomBooking>> result = validator.validate(roomBooking);

       assertEquals(1, result.size());
       assertEquals("must not be null", result.iterator().next().getMessage());
       assertEquals("rooms", result.iterator().next().getPropertyPath().toString());

    }

    @Test
    void b6010201_testBedMustNotBeNull() {
        Students students = new Students("Pontep Thaweesup", "B6000783", "pontep", "1234");
        students = studentsRepository.saveAndFlush(students);
        Rooms rooms = new Rooms("7133");
        rooms = roomsRepository.saveAndFlush(rooms);
        Bed bed = new Bed("What's branch?");
        bed = bedRepository.saveAndFlush(bed);

       

        RoomBooking roomBooking = new RoomBooking(students,rooms,null,"ต้องการเตียงเสริม");

       Set<ConstraintViolation<RoomBooking>> result = validator.validate(roomBooking);

       assertEquals(1, result.size());
       assertEquals("must not be null", result.iterator().next().getMessage());
       assertEquals("bed", result.iterator().next().getPropertyPath().toString());

    }
    
    @Test
    void b6010201_testDetailNotLessThanMin() {
        Students students = new Students("Pontep Thaweesup", "B6000783", "pontep", "1234");
        students = studentsRepository.saveAndFlush(students);
        Rooms rooms = new Rooms("7133");
        rooms = roomsRepository.saveAndFlush(rooms);
        Bed bed = new Bed("What's branch?");
        bed = bedRepository.saveAndFlush(bed);

        RoomBooking roomBooking = new RoomBooking(students,rooms,bed,"ต้อง");

        Set<ConstraintViolation<RoomBooking>> result = validator.validate(roomBooking);

        assertEquals(1, result.size());
        assertEquals("size must be between 5 and 30", result.iterator().next().getMessage());
        assertEquals("detail", result.iterator().next().getPropertyPath().toString());

    }

    @Test
    void b6010201_testDetailNotGreaterThanMax() {
        Students students = new Students("Pontep Thaweesup", "B6000783", "pontep", "1234");
        students = studentsRepository.saveAndFlush(students);
        Rooms rooms = new Rooms("7133");
        rooms = roomsRepository.saveAndFlush(rooms);
        Bed bed = new Bed("What's branch?");
        bed = bedRepository.saveAndFlush(bed);

        RoomBooking roomBooking = new RoomBooking(students,rooms,bed,"ต้องการที่จะได้ A SE แต่คงจะเป็นไปได้ยาก งั้นขอแค่ C ก็พอครับ");

        Set<ConstraintViolation<RoomBooking>> result = validator.validate(roomBooking);

        assertEquals(1, result.size());
        assertEquals("size must be between 5 and 30", result.iterator().next().getMessage());
        assertEquals("detail", result.iterator().next().getPropertyPath().toString());

    }

}