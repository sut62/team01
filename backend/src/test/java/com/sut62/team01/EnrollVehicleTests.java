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

@DataJpaTest
public class EnrollVehicleTests {
    private Validator validator;

    @Autowired
    private EnrollVehicleRepository enrollVehicleRepository;

    @Autowired
    private VehicleTypeRepository vehicleTypeRepository;

    @Autowired
    private StaffRepository staffRepository;

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
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    // Test Insert data is pass.
    @Test
    void b6025502_testInsertDataToEnrollVehicleIsWork() {
        // จำลองข้อมูลในการบันทึก EnrollVehicle
        VehicleType v_type = new VehicleType();
        v_type.setType("รถยนต์");
        v_type = vehicleTypeRepository.saveAndFlush(v_type);
        Staff staff = new Staff("Natthawut Sunthornrot", "black", "asd123");
        staff = staffRepository.saveAndFlush(staff);
        Students students = new Students("Natthawut Sunthornrot", "B6025502", "วิศวกรรมศาสตร์", "black", "asdasd");
        students = studentsRepository.saveAndFlush(students);
        Rooms rooms = new Rooms("7133");
        rooms = roomsRepository.saveAndFlush(rooms);
        Bed bed = new Bed("What's bed?");
        bed = bedRepository.saveAndFlush(bed);
        RoomBooking rbk = new RoomBooking(students, rooms, bed, "pakorn@hotmail.com");
        rbk = roomBookingRepository.saveAndFlush(rbk);

        // สร้าง EnrollVehice และ setter ทุก field ของ EnrollVehicle
        EnrollVehicle en_Vehicle = new EnrollVehicle();
        en_Vehicle.setEnrollDate(new Date());
        en_Vehicle.setLicensePlate("SUT01");
        en_Vehicle.setBrandName("YAMAHA");
        en_Vehicle.setOtherDetails("Fino สีดำ-แดง");
        en_Vehicle.setTypeOfVehicle(v_type);
        en_Vehicle.setCreatedBy(staff);
        en_Vehicle.setEnrolledStudents(rbk);

        // บันทึก EnrollVehicle
        en_Vehicle = enrollVehicleRepository.saveAndFlush(en_Vehicle);

        Optional<EnrollVehicle> found = enrollVehicleRepository.findById(en_Vehicle.getId());
        assertEquals(en_Vehicle, found.get());
    }

    // Test must match pattern.
    @Test
    void b6025502_testLicensePlateMustMatchPattern() {
        // จำลองข้อมูลในการบันทึก EnrollVehicle
        VehicleType v_type = new VehicleType();
        v_type.setType("รถยนต์");
        v_type = vehicleTypeRepository.saveAndFlush(v_type);
        Staff staff = new Staff("Natthawut Sunthornrot", "black", "asd123");
        staff = staffRepository.saveAndFlush(staff);
        Students students = new Students("Natthawut Sunthornrot", "B6025502", "วิศวกรรมศาสตร์", "black", "asdasd");
        students = studentsRepository.saveAndFlush(students);
        Rooms rooms = new Rooms("7133");
        rooms = roomsRepository.saveAndFlush(rooms);
        Bed bed = new Bed("What's bed?");
        bed = bedRepository.saveAndFlush(bed);
        RoomBooking rbk = new RoomBooking(students, rooms, bed, "pakorn@hotmail.com");
        rbk = roomBookingRepository.saveAndFlush(rbk);

        // สร้าง EnrollVehice และ setter ทุก field ของ EnrollVehicle
        EnrollVehicle en_Vehicle = new EnrollVehicle();
        en_Vehicle.setEnrollDate(new Date());
        en_Vehicle.setLicensePlate("01SUT");
        en_Vehicle.setBrandName("YAMAHA");
        en_Vehicle.setOtherDetails("Fino สีดำ-แดง");
        en_Vehicle.setTypeOfVehicle(v_type);
        en_Vehicle.setCreatedBy(staff);
        en_Vehicle.setEnrolledStudents(rbk);

        Set<ConstraintViolation<EnrollVehicle>> result = validator.validate(en_Vehicle);

        // ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรง และถูก field
        ConstraintViolation<EnrollVehicle> show = result.iterator().next();
        assertEquals("must match \"[A-Z]{3}\\d+\"", show.getMessage());
        assertEquals("licensePlate", show.getPropertyPath().toString());
    }

    // Test must not be null.
    @Test
    void b6025502_testEnrollDateMustNotBeNull() {
        // จำลองข้อมูลในการบันทึก EnrollVehicle
        VehicleType v_type = new VehicleType();
        v_type.setType("รถยนต์");
        v_type = vehicleTypeRepository.saveAndFlush(v_type);
        Staff staff = new Staff("Natthawut Sunthornrot", "black", "asd123");
        staff = staffRepository.saveAndFlush(staff);
        Students students = new Students("Natthawut Sunthornrot", "B6025502", "วิศวกรรมศาสตร์", "black", "asdasd");
        students = studentsRepository.saveAndFlush(students);
        Rooms rooms = new Rooms("7133");
        rooms = roomsRepository.saveAndFlush(rooms);
        Bed bed = new Bed("What's bed?");
        bed = bedRepository.saveAndFlush(bed);
        RoomBooking rbk = new RoomBooking(students, rooms, bed, "pakorn@hotmail.com");
        rbk = roomBookingRepository.saveAndFlush(rbk);

        // สร้าง EnrollVehice และ setter ทุก field ของ EnrollVehicle
        EnrollVehicle en_Vehicle = new EnrollVehicle();
        // set ให้ enrollDate เป็น Null
        en_Vehicle.setEnrollDate(null);
        en_Vehicle.setLicensePlate("SUT01");
        en_Vehicle.setBrandName("YAMAHA");
        en_Vehicle.setOtherDetails("Fino สีดำ-แดง");
        en_Vehicle.setTypeOfVehicle(v_type);
        en_Vehicle.setCreatedBy(staff);
        en_Vehicle.setEnrolledStudents(rbk);

        Set<ConstraintViolation<EnrollVehicle>> result = validator.validate(en_Vehicle);

        // ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรง และถูก field
        ConstraintViolation<EnrollVehicle> show = result.iterator().next();
        assertEquals("must not be null", show.getMessage());
        assertEquals("enrollDate", show.getPropertyPath().toString());
    }

    @Test
    void b6025502_testLicensePlateMustNotBeNull() {
        // จำลองข้อมูลในการบันทึก EnrollVehicle
        VehicleType v_type = new VehicleType();
        v_type.setType("รถยนต์");
        v_type = vehicleTypeRepository.saveAndFlush(v_type);
        Staff staff = new Staff("Natthawut Sunthornrot", "black", "asd123");
        staff = staffRepository.saveAndFlush(staff);
        Students students = new Students("Natthawut Sunthornrot", "B6025502", "วิศวกรรมศาสตร์", "black", "asdasd");
        students = studentsRepository.saveAndFlush(students);
        Rooms rooms = new Rooms("7133");
        rooms = roomsRepository.saveAndFlush(rooms);
        Bed bed = new Bed("What's bed?");
        bed = bedRepository.saveAndFlush(bed);
        RoomBooking rbk = new RoomBooking(students, rooms, bed, "pakorn@hotmail.com");
        rbk = roomBookingRepository.saveAndFlush(rbk);

        // สร้าง EnrollVehice และ setter ทุก field ของ EnrollVehicle
        EnrollVehicle en_Vehicle = new EnrollVehicle();
        en_Vehicle.setEnrollDate(new Date());
        // set ให้ licensePlate เป็น Null
        en_Vehicle.setLicensePlate(null);
        en_Vehicle.setBrandName("YAMAHA");
        en_Vehicle.setOtherDetails("Fino สีดำ-แดง");
        en_Vehicle.setTypeOfVehicle(v_type);
        en_Vehicle.setCreatedBy(staff);
        en_Vehicle.setEnrolledStudents(rbk);

        Set<ConstraintViolation<EnrollVehicle>> result = validator.validate(en_Vehicle);

        // ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรง และถูก field
        ConstraintViolation<EnrollVehicle> show = result.iterator().next();
        assertEquals("must not be null", show.getMessage());
        assertEquals("licensePlate", show.getPropertyPath().toString());
    }

    @Test
    void b6025502_testBrandNameMustNotBeNull() {
        // จำลองข้อมูลในการบันทึก EnrollVehicle
        VehicleType v_type = new VehicleType();
        v_type.setType("รถยนต์");
        v_type = vehicleTypeRepository.saveAndFlush(v_type);
        Staff staff = new Staff("Natthawut Sunthornrot", "black", "asd123");
        staff = staffRepository.saveAndFlush(staff);
        Students students = new Students("Natthawut Sunthornrot", "B6025502", "วิศวกรรมศาสตร์", "black", "asdasd");
        students = studentsRepository.saveAndFlush(students);
        Rooms rooms = new Rooms("7133");
        rooms = roomsRepository.saveAndFlush(rooms);
        Bed bed = new Bed("What's bed?");
        bed = bedRepository.saveAndFlush(bed);
        RoomBooking rbk = new RoomBooking(students, rooms, bed, "pakorn@hotmail.com");
        rbk = roomBookingRepository.saveAndFlush(rbk);

        // สร้าง EnrollVehice และ setter ทุก field ของ EnrollVehicle
        EnrollVehicle en_Vehicle = new EnrollVehicle();
        en_Vehicle.setEnrollDate(new Date());
        en_Vehicle.setLicensePlate("SUT01");
        // set ให้ brandName เป็น Null
        en_Vehicle.setBrandName(null);
        en_Vehicle.setOtherDetails("Fino สีดำ-แดง");
        en_Vehicle.setTypeOfVehicle(v_type);
        en_Vehicle.setCreatedBy(staff);
        en_Vehicle.setEnrolledStudents(rbk);

        Set<ConstraintViolation<EnrollVehicle>> result = validator.validate(en_Vehicle);

        // ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรง และถูก field
        ConstraintViolation<EnrollVehicle> show = result.iterator().next();
        assertEquals("must not be null", show.getMessage());
        assertEquals("brandName", show.getPropertyPath().toString());
    }

    @Test
    void b6025502_testOtherDetailsMustNotBeNull() {
        // จำลองข้อมูลในการบันทึก EnrollVehicle
        VehicleType v_type = new VehicleType();
        v_type.setType("รถยนต์");
        v_type = vehicleTypeRepository.saveAndFlush(v_type);
        Staff staff = new Staff("Natthawut Sunthornrot", "black", "asd123");
        staff = staffRepository.saveAndFlush(staff);
        Students students = new Students("Natthawut Sunthornrot", "B6025502", "วิศวกรรมศาสตร์", "black", "asdasd");
        students = studentsRepository.saveAndFlush(students);
        Rooms rooms = new Rooms("7133");
        rooms = roomsRepository.saveAndFlush(rooms);
        Bed bed = new Bed("What's bed?");
        bed = bedRepository.saveAndFlush(bed);
        RoomBooking rbk = new RoomBooking(students, rooms, bed, "pakorn@hotmail.com");
        rbk = roomBookingRepository.saveAndFlush(rbk);

        // สร้าง EnrollVehice และ setter ทุก field ของ EnrollVehicle
        EnrollVehicle en_Vehicle = new EnrollVehicle();
        en_Vehicle.setEnrollDate(new Date());
        en_Vehicle.setLicensePlate("SUT01");
        en_Vehicle.setBrandName("YAMAHA");
        // set ให้ otherDetails เป็น Null
        en_Vehicle.setOtherDetails(null);
        en_Vehicle.setTypeOfVehicle(v_type);
        en_Vehicle.setCreatedBy(staff);
        en_Vehicle.setEnrolledStudents(rbk);

        Set<ConstraintViolation<EnrollVehicle>> result = validator.validate(en_Vehicle);

        // ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรง และถูก field
        ConstraintViolation<EnrollVehicle> show = result.iterator().next();
        assertEquals("must not be null", show.getMessage());
        assertEquals("otherDetails", show.getPropertyPath().toString());
    }

    @Test
    void b6025502_testTypeOfVehicleMustNotBeNull() {
        // จำลองข้อมูลในการบันทึก EnrollVehicle
        VehicleType v_type = new VehicleType();
        v_type.setType("รถยนต์");
        v_type = vehicleTypeRepository.saveAndFlush(v_type);
        Staff staff = new Staff("Natthawut Sunthornrot", "black", "asd123");
        staff = staffRepository.saveAndFlush(staff);
        Students students = new Students("Natthawut Sunthornrot", "B6025502", "วิศวกรรมศาสตร์", "black", "asdasd");
        students = studentsRepository.saveAndFlush(students);
        Rooms rooms = new Rooms("7133");
        rooms = roomsRepository.saveAndFlush(rooms);
        Bed bed = new Bed("What's bed?");
        bed = bedRepository.saveAndFlush(bed);
        RoomBooking rbk = new RoomBooking(students, rooms, bed, "pakorn@hotmail.com");
        rbk = roomBookingRepository.saveAndFlush(rbk);

        // สร้าง EnrollVehice และ setter ทุก field ของ EnrollVehicle
        EnrollVehicle en_Vehicle = new EnrollVehicle();
        en_Vehicle.setEnrollDate(new Date());
        en_Vehicle.setLicensePlate("SUT01");
        en_Vehicle.setBrandName("YAMAHA");
        en_Vehicle.setOtherDetails("Fino สีดำ-แดง");
        // set ให้ typeOfVehicle เป็น Null
        en_Vehicle.setTypeOfVehicle(null);
        en_Vehicle.setCreatedBy(staff);
        en_Vehicle.setEnrolledStudents(rbk);

        Set<ConstraintViolation<EnrollVehicle>> result = validator.validate(en_Vehicle);

        // ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรง และถูก field
        ConstraintViolation<EnrollVehicle> show = result.iterator().next();
        assertEquals("must not be null", show.getMessage());
        assertEquals("typeOfVehicle", show.getPropertyPath().toString());
    }

    @Test
    void b6025502_testCreatedByMustNotBeNull() {
        // จำลองข้อมูลในการบันทึก EnrollVehicle
        VehicleType v_type = new VehicleType();
        v_type.setType("รถยนต์");
        v_type = vehicleTypeRepository.saveAndFlush(v_type);
        Staff staff = new Staff("Natthawut Sunthornrot", "black", "asd123");
        staff = staffRepository.saveAndFlush(staff);
        Students students = new Students("Natthawut Sunthornrot", "B6025502", "วิศวกรรมศาสตร์", "black", "asdasd");
        students = studentsRepository.saveAndFlush(students);
        Rooms rooms = new Rooms("7133");
        rooms = roomsRepository.saveAndFlush(rooms);
        Bed bed = new Bed("What's bed?");
        bed = bedRepository.saveAndFlush(bed);
        RoomBooking rbk = new RoomBooking(students, rooms, bed, "pakorn@hotmail.com");
        rbk = roomBookingRepository.saveAndFlush(rbk);

        // สร้าง EnrollVehice และ setter ทุก field ของ EnrollVehicle
        EnrollVehicle en_Vehicle = new EnrollVehicle();
        en_Vehicle.setEnrollDate(new Date());
        en_Vehicle.setLicensePlate("SUT01");
        en_Vehicle.setBrandName("YAMAHA");
        en_Vehicle.setOtherDetails("Fino สีดำ-แดง");
        en_Vehicle.setTypeOfVehicle(v_type);
        // set ให้ createdBy เป็น Null
        en_Vehicle.setCreatedBy(null);
        en_Vehicle.setEnrolledStudents(rbk);

        Set<ConstraintViolation<EnrollVehicle>> result = validator.validate(en_Vehicle);

        // ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรง และถูก field
        ConstraintViolation<EnrollVehicle> show = result.iterator().next();
        assertEquals("must not be null", show.getMessage());
        assertEquals("createdBy", show.getPropertyPath().toString());
    }

    @Test
    void b6025502_testEnrolledStudentsMustNotBeNull() {
        // จำลองข้อมูลในการบันทึก EnrollVehicle
        VehicleType v_type = new VehicleType();
        v_type.setType("รถยนต์");
        v_type = vehicleTypeRepository.saveAndFlush(v_type);
        Staff staff = new Staff("Natthawut Sunthornrot", "black", "asd123");
        staff = staffRepository.saveAndFlush(staff);
        Students students = new Students("Natthawut Sunthornrot", "B6025502", "วิศวกรรมศาสตร์", "black", "asdasd");
        students = studentsRepository.saveAndFlush(students);
        Rooms rooms = new Rooms("7133");
        rooms = roomsRepository.saveAndFlush(rooms);
        Bed bed = new Bed("What's bed?");
        bed = bedRepository.saveAndFlush(bed);
        RoomBooking rbk = new RoomBooking(students, rooms, bed, "pakorn@hotmail.com");
        rbk = roomBookingRepository.saveAndFlush(rbk);

        // สร้าง EnrollVehice และ setter ทุก field ของ EnrollVehicle
        EnrollVehicle en_Vehicle = new EnrollVehicle();
        en_Vehicle.setEnrollDate(new Date());
        en_Vehicle.setLicensePlate("SUT01");
        en_Vehicle.setBrandName("YAMAHA");
        en_Vehicle.setOtherDetails("Fino สีดำ-แดง");
        en_Vehicle.setTypeOfVehicle(v_type);
        en_Vehicle.setCreatedBy(staff);
        // set ให้ enrolledStudents เป็น Null
        en_Vehicle.setEnrolledStudents(null);

        Set<ConstraintViolation<EnrollVehicle>> result = validator.validate(en_Vehicle);

        // ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรง และถูก field
        ConstraintViolation<EnrollVehicle> show = result.iterator().next();
        assertEquals("must not be null", show.getMessage());
        assertEquals("enrolledStudents", show.getPropertyPath().toString());
    }

    // Test size must be between ... and ...
    @Test
    void b6025502_testLicensePlateMustLessThanOrEqualTo7() {
        // จำลองข้อมูลในการบันทึก EnrollVehicle
        VehicleType v_type = new VehicleType();
        v_type.setType("รถยนต์");
        v_type = vehicleTypeRepository.saveAndFlush(v_type);
        Staff staff = new Staff("Natthawut Sunthornrot", "black", "asd123");
        staff = staffRepository.saveAndFlush(staff);
        Students students = new Students("Natthawut Sunthornrot", "B6025502", "วิศวกรรมศาสตร์", "black", "asdasd");
        students = studentsRepository.saveAndFlush(students);
        Rooms rooms = new Rooms("7133");
        rooms = roomsRepository.saveAndFlush(rooms);
        Bed bed = new Bed("What's bed?");
        bed = bedRepository.saveAndFlush(bed);
        RoomBooking rbk = new RoomBooking(students, rooms, bed, "pakorn@hotmail.com");
        rbk = roomBookingRepository.saveAndFlush(rbk);

        // สร้าง EnrollVehice และ setter ทุก field ของ EnrollVehicle
        EnrollVehicle en_Vehicle = new EnrollVehicle();
        en_Vehicle.setEnrollDate(new Date());
        en_Vehicle.setLicensePlate("SUT123456");
        en_Vehicle.setBrandName("YAMAHA");
        en_Vehicle.setOtherDetails("Fino สีดำ-แดง");
        en_Vehicle.setTypeOfVehicle(v_type);
        en_Vehicle.setCreatedBy(staff);
        en_Vehicle.setEnrolledStudents(rbk);

        Set<ConstraintViolation<EnrollVehicle>> result = validator.validate(en_Vehicle);

        // ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรง และถูก field
        ConstraintViolation<EnrollVehicle> show = result.iterator().next();
        assertEquals("size must be between 5 and 7", show.getMessage());
        assertEquals("licensePlate", show.getPropertyPath().toString());
    }

    @Test
    void b6025502_testLicensePlateMustGreaterThanOrEqualTo4() {
        // จำลองข้อมูลในการบันทึก EnrollVehicle
        VehicleType v_type = new VehicleType();
        v_type.setType("รถยนต์");
        v_type = vehicleTypeRepository.saveAndFlush(v_type);
        Staff staff = new Staff("Natthawut Sunthornrot", "black", "asd123");
        staff = staffRepository.saveAndFlush(staff);
        Students students = new Students("Natthawut Sunthornrot", "B6025502", "วิศวกรรมศาสตร์", "black", "asdasd");
        students = studentsRepository.saveAndFlush(students);
        Rooms rooms = new Rooms("7133");
        rooms = roomsRepository.saveAndFlush(rooms);
        Bed bed = new Bed("What's bed?");
        bed = bedRepository.saveAndFlush(bed);
        RoomBooking rbk = new RoomBooking(students, rooms, bed, "pakorn@hotmail.com");
        rbk = roomBookingRepository.saveAndFlush(rbk);

        // สร้าง EnrollVehice และ setter ทุก field ของ EnrollVehicle
        EnrollVehicle en_Vehicle = new EnrollVehicle();
        en_Vehicle.setEnrollDate(new Date());
        en_Vehicle.setLicensePlate("SUT0");
        en_Vehicle.setBrandName("YAMAHA");
        en_Vehicle.setOtherDetails("Fino สีดำ-แดง");
        en_Vehicle.setTypeOfVehicle(v_type);
        en_Vehicle.setCreatedBy(staff);
        en_Vehicle.setEnrolledStudents(rbk);

        Set<ConstraintViolation<EnrollVehicle>> result = validator.validate(en_Vehicle);

        // ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรง และถูก field
        ConstraintViolation<EnrollVehicle> show = result.iterator().next();
        assertEquals("size must be between 5 and 7", show.getMessage());
        assertEquals("licensePlate", show.getPropertyPath().toString());
    }
}