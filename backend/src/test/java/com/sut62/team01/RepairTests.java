package com.sut62.team01;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Date;
import java.util.Optional;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import com.sut62.team01.entity.DeviceName;
import com.sut62.team01.entity.DeviceType;
import com.sut62.team01.entity.Repair;
import com.sut62.team01.entity.RoomBooking;
import com.sut62.team01.entity.Rooms;
import com.sut62.team01.entity.Students;
import com.sut62.team01.entity.Bed;
import com.sut62.team01.repository.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class RepairTests {

    private Validator validator;

    @Autowired
    private RepairRepository repairRepository;

    @Autowired
    private DeviceTypeRepository deviceTypeRepository;

    @Autowired
    private DeviceNameRepository deviceNameRepository;

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
    @Test
    void b6004897_testInsertDataToRepairOK() {
        // จำลองข้อมูลที่จำเป็นในการบันทึก Repair
        DeviceType deviceType = new DeviceType("อุปกรณ์ไฟฟ้า");
        deviceType = deviceTypeRepository.saveAndFlush(deviceType);
        DeviceName deviceName = new DeviceName();
        deviceName.setName("หลอดไฟ");
        deviceName = deviceNameRepository.saveAndFlush(deviceName);
        Students students = new Students("Pontep Thaweesup", "B6000783","วิศวกรรมศาสตร์", "pontep", "1234");
        students = studentsRepository.saveAndFlush(students);
        Rooms rooms = new Rooms("7133");
        rooms = roomsRepository.saveAndFlush(rooms);
        Bed bed = new Bed("What's bed?");
        bed = bedRepository.saveAndFlush(bed);
        RoomBooking roomBooking = new RoomBooking(students, rooms, bed,"pakorn@hotmail.com");
        roomBooking = roomBookingRepository.saveAndFlush(roomBooking);

        // สร้าง Repair และ set ค่าต่างๆ
        Repair repair = new Repair();

        repair.setEnrolled(roomBooking);
        repair.setRepairDate(new Date());
        repair.setType(deviceType);
        repair.setName(deviceName);
        repair.setList("หลอดไฟขาด");
        repair.setTel("0924184558");
                
        // บันทึก repair
       repair = repairRepository.saveAndFlush(repair);
        // เรียก repair ที่พึ่งบันทึกขึ้นมาดู
        Optional<Repair> found = repairRepository.findById(repair.getId());
        // assert check
        assertEquals(repair, found.get());
    }


    @Test
    void b6004897_testListMustBeGreaterEqual3() {
        DeviceType deviceType = new DeviceType("อุปกรณ์ไฟฟ้า");
        deviceType  = deviceTypeRepository.saveAndFlush(deviceType);
        
        DeviceName deviceName = new DeviceName();
        deviceName.setName("ตู้เย็น");
        deviceName = deviceNameRepository.saveAndFlush(deviceName);
        Students students = new Students("Pontep Thaweesup", "B6000783", "วิศวกรรมศาสตร์","pontep", "1234");
        students = studentsRepository.saveAndFlush(students);
        Rooms rooms = new Rooms("7133");
        rooms = roomsRepository.saveAndFlush(rooms);
        Bed bed = new Bed("What's bed?");
        bed = bedRepository.saveAndFlush(bed);
        RoomBooking roomBooking = new RoomBooking(students, rooms, bed,"pakorn@hotmail.com");
        roomBooking = roomBookingRepository.saveAndFlush(roomBooking);

        // สร้าง Repair และ set ค่าต่างๆ
        Repair repair = new Repair();
        repair.setType(deviceType);
        repair.setName(deviceName);
        repair.setRepairDate(new Date());
        repair.setEnrolled(roomBooking);
        repair.setList("12");
        repair.setTel("0924184558");
        // validate Repair
        Set<ConstraintViolation<Repair>> result = validator.validate(repair);
        // ต้องมี 1 Error
        assertEquals(1, result.size());
        // error message ตรงชนิด และ ถูก field
        assertEquals("size must be between 3 and 30", result.iterator().next().getMessage());
        assertEquals("list", result.iterator().next().getPropertyPath().toString());
    }


    @Test
    void b6004897_testListMustBeLessEqual30() {
        DeviceType deviceType = new DeviceType("อุปกรณ์ไฟฟ้า");
        deviceType  = deviceTypeRepository.saveAndFlush(deviceType);
        
        DeviceName deviceName = new DeviceName();
        deviceName.setName("ตู้เย็น");
        deviceName = deviceNameRepository.saveAndFlush(deviceName);
        Students students = new Students("Pontep Thaweesup", "B6000783", "วิศวกรรมศาสตร์","pontep", "1234");
        students = studentsRepository.saveAndFlush(students);
        Rooms rooms = new Rooms("7133");
        rooms = roomsRepository.saveAndFlush(rooms);
        Bed bed = new Bed("What's bed?");
        bed = bedRepository.saveAndFlush(bed);
        RoomBooking roomBooking = new RoomBooking(students, rooms, bed,"pakorn@hotmail.com");
        roomBooking = roomBookingRepository.saveAndFlush(roomBooking);

        // สร้าง Repair และ set ค่าต่างๆ
        Repair repair = new Repair();
        repair.setType(deviceType);
        repair.setName(deviceName);
        repair.setRepairDate(new Date());
        repair.setEnrolled(roomBooking);
        repair.setList("1234567890123456789012345678901");
        repair.setTel("0924184558");
        // validate Repair
        Set<ConstraintViolation<Repair>> result = validator.validate(repair);
        // ต้องมี 1 Error
        assertEquals(1, result.size());
        // error message ตรงชนิด และ ถูก field
        assertEquals("size must be between 3 and 30", result.iterator().next().getMessage());
        assertEquals("list", result.iterator().next().getPropertyPath().toString());
    }


    @Test
    void b6004897_testEnrolledMustBeNull() {
        DeviceType deviceType = new DeviceType("อุปกรณ์ไฟฟ้า");
        deviceType  = deviceTypeRepository.saveAndFlush(deviceType);
        
        DeviceName deviceName = new DeviceName();
        deviceName.setName("ตู้เย็น");
        deviceName = deviceNameRepository.saveAndFlush(deviceName);
        Students students = new Students("Pontep Thaweesup", "B6000783","วิศวกรรมศาสตร์", "pontep", "1234");
        students = studentsRepository.saveAndFlush(students);
        Rooms rooms = new Rooms("7133");
        rooms = roomsRepository.saveAndFlush(rooms);
        Bed bed = new Bed("What's bed?");
        bed = bedRepository.saveAndFlush(bed);
        RoomBooking roomBooking = new RoomBooking(students, rooms, bed,"pakorn@hotmail.com");
        roomBooking = roomBookingRepository.saveAndFlush(roomBooking);

        // สร้าง Repair และ set ค่าต่างๆ
        Repair repair = new Repair();
        repair.setType(deviceType);
        repair.setName(deviceName);
        repair.setRepairDate(new Date());
        repair.setEnrolled(null);
        repair.setList("เปิดไม่ติด");
        repair.setTel("0924184558");
        // validate Repair
        Set<ConstraintViolation<Repair>> result = validator.validate(repair);
        // ต้องมี 1 Error
        assertEquals(1, result.size());
        // error message ตรงชนิด และ ถูก field
        assertEquals("must not be null", result.iterator().next().getMessage());
        assertEquals("enrolled", result.iterator().next().getPropertyPath().toString());
    }
    @Test
    void b6004897_testDeviceTypeMustBeNull() {
        DeviceType deviceType = new DeviceType("อุปกรณ์ไฟฟ้า");
        deviceType  = deviceTypeRepository.saveAndFlush(deviceType);
        
        DeviceName deviceName = new DeviceName();
        deviceName.setName("ตู้เย็น");
        deviceName = deviceNameRepository.saveAndFlush(deviceName);
        Students students = new Students("Pontep Thaweesup", "B6000783","วิศวกรรมศาสตร์", "pontep", "1234");
        students = studentsRepository.saveAndFlush(students);
        Rooms rooms = new Rooms("7133");
        rooms = roomsRepository.saveAndFlush(rooms);
        Bed bed = new Bed("What's bed?");
        bed = bedRepository.saveAndFlush(bed);
        RoomBooking roomBooking = new RoomBooking(students, rooms, bed,"pakorn@hotmail.com");
        roomBooking = roomBookingRepository.saveAndFlush(roomBooking);

        // สร้าง Repair และ set ค่าต่างๆ
        Repair repair = new Repair();
        repair.setType(null);
        repair.setName(deviceName);
        repair.setRepairDate(new Date());
        repair.setEnrolled(roomBooking);
        repair.setList("เปิดไม่ติด");
        repair.setTel("0924184558");
        // validate Repair
        Set<ConstraintViolation<Repair>> result = validator.validate(repair);
        // ต้องมี 1 Error
        assertEquals(1, result.size());
        // error message ตรงชนิด และ ถูก field
        assertEquals("must not be null", result.iterator().next().getMessage());
        assertEquals("type", result.iterator().next().getPropertyPath().toString());
    }

    
    @Test
    void b6004897_testDeviceNameMustBeNull() {
        DeviceType deviceType = new DeviceType("อุปกรณ์ไฟฟ้า");
        deviceType  = deviceTypeRepository.saveAndFlush(deviceType);
        
        DeviceName deviceName = new DeviceName();
        deviceName.setName("ตู้เย็น");
        deviceName = deviceNameRepository.saveAndFlush(deviceName);
        Students students = new Students("Pontep Thaweesup", "B6000783","วิศวกรรมศาสตร์", "pontep", "1234");
        students = studentsRepository.saveAndFlush(students);
        Rooms rooms = new Rooms("7133");
        rooms = roomsRepository.saveAndFlush(rooms);
        Bed bed = new Bed("What's bed?");
        bed = bedRepository.saveAndFlush(bed);
        RoomBooking roomBooking = new RoomBooking(students, rooms, bed,"pakorn@hotmail.com");
        roomBooking = roomBookingRepository.saveAndFlush(roomBooking);

        // สร้าง Repair และ set ค่าต่างๆ
        Repair repair = new Repair();
        repair.setType(deviceType);
        repair.setName(null);
        repair.setRepairDate(new Date());
        repair.setEnrolled(roomBooking);
        repair.setList("เปิดไม่ติด");
        repair.setTel("0924184558");
        // validate Repair
        Set<ConstraintViolation<Repair>> result = validator.validate(repair);
        // ต้องมี 1 Error
        assertEquals(1, result.size());
        // error message ตรงชนิด และ ถูก field
        assertEquals("must not be null", result.iterator().next().getMessage());
        assertEquals("name", result.iterator().next().getPropertyPath().toString());
    }

    @Test
    void b6004897_testRepairDateMustBeNull() {
        DeviceType deviceType = new DeviceType("อุปกรณ์ไฟฟ้า");
        deviceType  = deviceTypeRepository.saveAndFlush(deviceType);
        
        DeviceName deviceName = new DeviceName();
        deviceName.setName("ตู้เย็น");
        deviceName = deviceNameRepository.saveAndFlush(deviceName);
        Students students = new Students("Pontep Thaweesup", "B6000783","วิศวกรรมศาสตร์", "pontep", "1234");
        students = studentsRepository.saveAndFlush(students);
        Rooms rooms = new Rooms("7133");
        rooms = roomsRepository.saveAndFlush(rooms);
        Bed bed = new Bed("What's bed?");
        bed = bedRepository.saveAndFlush(bed);
        RoomBooking roomBooking = new RoomBooking(students, rooms, bed,"pakorn@hotmail.com");
        roomBooking = roomBookingRepository.saveAndFlush(roomBooking);

        // สร้าง Repair และ set ค่าต่างๆ
        Repair repair = new Repair();
        repair.setType(deviceType);
        repair.setName(deviceName);
        repair.setRepairDate(null);
        repair.setEnrolled(roomBooking);
        repair.setList("เปิดไม่ติด");
        repair.setTel("0924184558");
        // validate Repair
        Set<ConstraintViolation<Repair>> result = validator.validate(repair);
        // ต้องมี 1 Error
        assertEquals(1, result.size());
        // error message ตรงชนิด และ ถูก field
        assertEquals("must not be null", result.iterator().next().getMessage());
        assertEquals("repairDate", result.iterator().next().getPropertyPath().toString());
    }

   
    @Test
    void b6004897_testTel9Digit() {
        DeviceType deviceType = new DeviceType("อุปกรณ์ไฟฟ้า");
        deviceType  = deviceTypeRepository.saveAndFlush(deviceType);
        
        DeviceName deviceName = new DeviceName();
        deviceName.setName("ตู้เย็น");
        deviceName = deviceNameRepository.saveAndFlush(deviceName);
        Students students = new Students("Pontep Thaweesup", "B6000783","วิศวกรรมศาสตร์", "pontep", "1234");
        students = studentsRepository.saveAndFlush(students);
        Rooms rooms = new Rooms("7133");
        rooms = roomsRepository.saveAndFlush(rooms);
        Bed bed = new Bed("What's bed?");
        bed = bedRepository.saveAndFlush(bed);
        RoomBooking roomBooking = new RoomBooking(students, rooms, bed,"pakorn@hotmail.com");
        roomBooking = roomBookingRepository.saveAndFlush(roomBooking);

        // สร้าง Repair และ set ค่าต่างๆ
        Repair repair = new Repair();
        repair.setType(deviceType);
        repair.setName(deviceName);
        repair.setRepairDate(new Date());
        repair.setEnrolled(roomBooking);
        repair.setList("เปิดไม่ติด");
        repair.setTel("012345678");
        // validate Repair
        Set<ConstraintViolation<Repair>> result = validator.validate(repair);
        // ต้องมี 1 Error
        assertEquals(1, result.size());
        // error message ตรงชนิด และ ถูก field
        assertEquals("must match \"\\d{10}\"", result.iterator().next().getMessage());
        assertEquals("tel", result.iterator().next().getPropertyPath().toString());
    }

    @Test
    void b6004897_testTel11Digit() {
        DeviceType deviceType = new DeviceType("อุปกรณ์ไฟฟ้า");
        deviceType  = deviceTypeRepository.saveAndFlush(deviceType);
        
        DeviceName deviceName = new DeviceName();
        deviceName.setName("ตู้เย็น");
        deviceName = deviceNameRepository.saveAndFlush(deviceName);
        Students students = new Students("Pontep Thaweesup", "B6000783","วิศวกรรมศาสตร์", "pontep", "1234");
        students = studentsRepository.saveAndFlush(students);
        Rooms rooms = new Rooms("7133");
        rooms = roomsRepository.saveAndFlush(rooms);
        Bed bed = new Bed("What's bed?");
        bed = bedRepository.saveAndFlush(bed);
        RoomBooking roomBooking = new RoomBooking(students, rooms, bed,"pakorn@hotmail.com");
        roomBooking = roomBookingRepository.saveAndFlush(roomBooking);

        // สร้าง Repair และ set ค่าต่างๆ
        Repair repair = new Repair();
        repair.setType(deviceType);
        repair.setName(deviceName);
        repair.setRepairDate(new Date());
        repair.setEnrolled(roomBooking);
        repair.setList("เปิดไม่ติด");
        repair.setTel("01234567890");
        // validate Repair
        Set<ConstraintViolation<Repair>> result = validator.validate(repair);
        // ต้องมี 1 Error
        assertEquals(1, result.size());
        // error message ตรงชนิด และ ถูก field
        assertEquals("must match \"\\d{10}\"", result.iterator().next().getMessage());
        assertEquals("tel", result.iterator().next().getPropertyPath().toString());
    }





}