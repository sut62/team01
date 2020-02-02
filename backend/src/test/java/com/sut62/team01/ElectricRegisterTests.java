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
 * ElectricRegisterTests
 */
@DataJpaTest
public class ElectricRegisterTests {

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
    private ElectricTypeRepository electricTypeRepository;

    @Autowired
    private StaffRepository staffRepository;

    @Autowired
    private ElectricalRegistrationRepository electricalRegistrationRepository;

    @BeforeEach
    public void setup() {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }

    @Test
    void b6003296_testInsertDataOk() {
        // create oj
        ElectricalRegistration electricalRegistration = new ElectricalRegistration();
        // necessary for roombooking
        Students students = new Students("Pontep Thaweesup", "B6000783","วิศวกรรมศาสตร์", "pontep", "1234");
        students = studentsRepository.saveAndFlush(students);
        Rooms rooms = new Rooms("7133");
        rooms = roomsRepository.saveAndFlush(rooms);
        Bed bed = new Bed("What's branch?");
        bed = bedRepository.saveAndFlush(bed);
        RoomBooking roomBooking = new RoomBooking(students, rooms, bed,"pakorn@hotmail.com");
        roomBooking = roomBookingRepository.saveAndFlush(roomBooking);
        // necessary for electricRegister
        ElectricType electricType = new ElectricType("asd");
        electricType = electricTypeRepository.saveAndFlush(electricType);
        Staff staff = new Staff("asdsa", "asdsa", "qwe");
        staff = staffRepository.saveAndFlush(staff);

        electricalRegistration.setRoomBooking(roomBooking);
        electricalRegistration.setElectricType(electricType);
        electricalRegistration.setStaff(staff);
        electricalRegistration.setDetails("details");
        electricalRegistration.setElectricalRegistrationdate(new Date());
        electricalRegistration = electricalRegistrationRepository.saveAndFlush(electricalRegistration);

        Optional<ElectricalRegistration> found = electricalRegistrationRepository
                .findById(electricalRegistration.getId());
        assertEquals(electricalRegistration, found.get());
    }

    @Test
    void b6003296_testStaffMustNotBeNull(){
        // create oj
        ElectricalRegistration electricalRegistration = new ElectricalRegistration();
        // necessary for roombooking
        Students students = new Students("Pontep Thaweesup", "B6000783","วิศวกรรมศาสตร์", "pontep", "1234");
        students = studentsRepository.saveAndFlush(students);
        Rooms rooms = new Rooms("7133");
        rooms = roomsRepository.saveAndFlush(rooms);
        Bed bed = new Bed("What's bed?");
        bed = bedRepository.saveAndFlush(bed);
        RoomBooking roomBooking = new RoomBooking(students, rooms, bed,"pakorn@hotmail.com");
        roomBooking = roomBookingRepository.saveAndFlush(roomBooking);
        // necessary for electricRegister
        ElectricType electricType = new ElectricType("asd");
        electricType = electricTypeRepository.saveAndFlush(electricType);
        Staff staff = new Staff("asdsa", "asdsa", "qwe");
        staff = staffRepository.saveAndFlush(staff);

        electricalRegistration.setRoomBooking(roomBooking);
        electricalRegistration.setElectricType(electricType);
        electricalRegistration.setStaff(null);
        electricalRegistration.setDetails("details");
        electricalRegistration.setElectricalRegistrationdate(new Date());

        Set<ConstraintViolation<ElectricalRegistration>> result = validator.validate(electricalRegistration);

        assertEquals(1,result.size());
        assertEquals("must not be null",result.iterator().next().getMessage());
        assertEquals("staff", result.iterator().next().getPropertyPath().toString());

    }

    @Test
    void b6003296_testElectricTypeMustNotBeNull(){

        // create oj
        ElectricalRegistration electricalRegistration = new ElectricalRegistration();
        // necessary for roombooking
        Students students = new Students("Pontep Thaweesup", "B6000783","วิศวกรรมศาสตร์", "pontep", "1234");
        students = studentsRepository.saveAndFlush(students);
        Rooms rooms = new Rooms("7133");
        rooms = roomsRepository.saveAndFlush(rooms);
        Bed bed = new Bed("What's bed?");
        bed = bedRepository.saveAndFlush(bed);
        RoomBooking roomBooking = new RoomBooking(students, rooms, bed,"pakorn@hotmail.com");
        roomBooking = roomBookingRepository.saveAndFlush(roomBooking);
        // necessary for electricRegister
        ElectricType electricType = new ElectricType("asd");
        electricType = electricTypeRepository.saveAndFlush(electricType);
        Staff staff = new Staff("asdsa", "asdsa", "qwe");
        staff = staffRepository.saveAndFlush(staff);

        electricalRegistration.setRoomBooking(roomBooking);
        electricalRegistration.setElectricType(null);
        electricalRegistration.setStaff(staff);
        electricalRegistration.setDetails("details");
        electricalRegistration.setElectricalRegistrationdate(new Date());

        Set<ConstraintViolation<ElectricalRegistration>> result = validator.validate(electricalRegistration);

        assertEquals(1,result.size());
        assertEquals("must not be null",result.iterator().next().getMessage());
        assertEquals("electricType", result.iterator().next().getPropertyPath().toString());

    }

    @Test
    void b6003296_testRoomBookingMustNoBeNull(){

        // create oj
        ElectricalRegistration electricalRegistration = new ElectricalRegistration();
        // necessary for roombooking
        Students students = new Students("Pontep Thaweesup", "B6000783","วิศวกรรมศาสตร์", "pontep", "1234");
        students = studentsRepository.saveAndFlush(students);
        Rooms rooms = new Rooms("7133");
        rooms = roomsRepository.saveAndFlush(rooms);
        Bed bed = new Bed("What's bed?");
        bed = bedRepository.saveAndFlush(bed);
        RoomBooking roomBooking = new RoomBooking(students, rooms, bed,"pakorn@hotmail.com");
        roomBooking = roomBookingRepository.saveAndFlush(roomBooking);
        // necessary for electricRegister
        ElectricType electricType = new ElectricType("asd");
        electricType = electricTypeRepository.saveAndFlush(electricType);
        Staff staff = new Staff("asdsa", "asdsa", "qwe");
        staff = staffRepository.saveAndFlush(staff);

        electricalRegistration.setRoomBooking(null);
        electricalRegistration.setElectricType(electricType);
        electricalRegistration.setStaff(staff);
        electricalRegistration.setDetails("details");
        electricalRegistration.setElectricalRegistrationdate(new Date());

        Set<ConstraintViolation<ElectricalRegistration>> result = validator.validate(electricalRegistration);

        assertEquals(1,result.size());
        assertEquals("must not be null",result.iterator().next().getMessage());
        assertEquals("roomBooking", result.iterator().next().getPropertyPath().toString());

    }

    @Test
    void B6003296_testDetailsustBeGreaterEqual5(){
        // create oj
        ElectricalRegistration electricalRegistration = new ElectricalRegistration();
        // necessary for roombooking
        Students students = new Students("Pontep Thaweesup", "B6000783","วิศวกรรมศาสตร์", "pontep", "1234");
        students = studentsRepository.saveAndFlush(students);
        Rooms rooms = new Rooms("7133");
        rooms = roomsRepository.saveAndFlush(rooms);
        Bed bed = new Bed("What's bed?");
        bed = bedRepository.saveAndFlush(bed);
        RoomBooking roomBooking = new RoomBooking(students, rooms, bed,"pakorn@hotmail.com");
        roomBooking = roomBookingRepository.saveAndFlush(roomBooking);
        // necessary for electricRegister
        ElectricType electricType = new ElectricType("asd");
        electricType = electricTypeRepository.saveAndFlush(electricType);
        Staff staff = new Staff("asdsa", "asdsa", "qwe");
        staff = staffRepository.saveAndFlush(staff);

        electricalRegistration.setRoomBooking(roomBooking);
        electricalRegistration.setElectricType(electricType);
        electricalRegistration.setStaff(staff);
        electricalRegistration.setDetails("1234");
        electricalRegistration.setElectricalRegistrationdate(new Date());

        Set<ConstraintViolation<ElectricalRegistration>> result = validator.validate(electricalRegistration);
        assertEquals(1,result.size());
        assertEquals("size must be between 5 and 30",result.iterator().next().getMessage());
        assertEquals("details",result.iterator().next().getPropertyPath().toString());
    }

    @Test
    void B6003296_testDetailsustBeLessEqual30(){
        // create oj
        ElectricalRegistration electricalRegistration = new ElectricalRegistration();
        // necessary for roombooking
        Students students = new Students("Pontep Thaweesup", "B6000783","วิศวกรรมศาสตร์", "pontep", "1234");
        students = studentsRepository.saveAndFlush(students);
        Rooms rooms = new Rooms("7133");
        rooms = roomsRepository.saveAndFlush(rooms);
        Bed bed = new Bed("What's bed?");
        bed = bedRepository.saveAndFlush(bed);
        RoomBooking roomBooking = new RoomBooking(students, rooms, bed,"pakorn@hotmail.com");
        roomBooking = roomBookingRepository.saveAndFlush(roomBooking);
        // necessary for electricRegister
        ElectricType electricType = new ElectricType("asd");
        electricType = electricTypeRepository.saveAndFlush(electricType);
        Staff staff = new Staff("asdsa", "asdsa", "qwe");
        staff = staffRepository.saveAndFlush(staff);

        electricalRegistration.setRoomBooking(roomBooking);
        electricalRegistration.setElectricType(electricType);
        electricalRegistration.setStaff(staff);
        electricalRegistration.setDetails("1234567890123456789012345678901");
        electricalRegistration.setElectricalRegistrationdate(new Date());

        Set<ConstraintViolation<ElectricalRegistration>> result = validator.validate(electricalRegistration);
        assertEquals(1,result.size());
        assertEquals("size must be between 5 and 30",result.iterator().next().getMessage());
        assertEquals("details",result.iterator().next().getPropertyPath().toString());
    }


}