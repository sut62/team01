package com.sut62.team01;

import java.util.Collections;
import java.util.stream.Stream;

import com.sut62.team01.entity.*;
import com.sut62.team01.repository.*;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@SpringBootApplication
public class SUT62TEAM01 {

    public static void main(String[] args) {
        SpringApplication.run(SUT62TEAM01.class, args);
    }

    @Bean
    ApplicationRunner init(BorrowedBikeRepository borrowedBikeRepository, BikeTypeRepository bikeTypeRepository,
            DateTypeRepository dateTypeRepository, RoomBookingRepository roomBookingRepository,
            StudentsRepository studentsRepository, BedRepository bedRepository, RoomsRepository roomsRepository,
            StaffRepository staffRepository, VehicleTypeRepository vehicleTypeRepository,
            ElectricTypeRepository electrictypeRepository, PackageTypeRepository packageTypeRepository,
            DeviceTypeRepository deviceTypeRepository, DeviceNameRepository deviceNameRepository,
            BikeRepository bikeRepository, PackageManagementRepository packageManagementRepository) {

        return args -> {

            // TODO: Inject test data
            // inject necessary data for katalon BorrowedBikeTest
            // BikeType
            BikeType bikeType1 = new BikeType("จักรยาน 2 ล้อ สำหรับคนเดียว");
            BikeType bikeType2 = new BikeType("จักรยาน 2 ล้อ สำหรับสองคน");
            BikeType bikeType3 = new BikeType("จักรยาน 3 ล้อ แบบมีหลังคา");
            BikeType bikeType4 = new BikeType("จักรยาน 3 ล้อ แบบไม่มีหลังคา");
            Stream.of(bikeType1, bikeType2, bikeType3, bikeType4).forEach(bikeType -> {
                bikeTypeRepository.save(bikeType);
                System.out.println(bikeType);
            });

            // Bike
            for (int i = 1; i <= 10; i++) {
                Bike bike = new Bike(bikeType1, "Bike" + "01" + (i < 10 ? "0" + i : i));
                bikeRepository.save(bike);
            }
            for (int i = 1; i <= 10; i++) {
                Bike bike = new Bike(bikeType2, "Bike" + "02" + (i < 10 ? "0" + i : i));
                bikeRepository.save(bike);
            }
            for (int i = 1; i <= 10; i++) {
                Bike bike = new Bike(bikeType3, "Bike" + "03" + (i < 10 ? "0" + i : i));
                bikeRepository.save(bike);
            }
            for (int i = 1; i <= 10; i++) {
                Bike bike = new Bike(bikeType4, "Bike" + "04" + (i < 10 ? "0" + i : i));
                bikeRepository.save(bike);
            }

            // DateType
            DateType dateType1 = new DateType("ยืม 3 วัน");
            DateType dateType2 = new DateType("ยืม 7 วัน");
            DateType dateType3 = new DateType("ยืม 1 ภาคการศึกษา");
            Stream.of(dateType1, dateType2, dateType3).forEach(dateType -> {
                dateTypeRepository.save(dateType);
                System.out.println(dateType);
            });

            // Students
            Students student1 = new Students("นายพรเทพ ทวีทรัพย์", "b6000783", "pontep", "1234");
            Students student2 = new Students("นายเจตุพนน์ ศรีภูธร", "b6000123", "jayza", "1234");
            Students student3 = new Students("นายภูมิรินทร์ เพียวสูงเนิน", "b6003296", "leo2541", "1234");
            Stream.of(student1, student2, student3).forEach(student -> {
                studentsRepository.save(student);
                System.out.println(student);
            });

            Bed bed1 = new Bed("เตียง A (ติดประตูทางเข้า)");
            Bed bed2 = new Bed("เตียง B (กลางห้อง)");
            Bed bed3 = new Bed("เตียง C (ติดประตูหลัง)");
            Stream.of(bed1, bed2, bed3).forEach(bed -> {
                        bedRepository.save(bed);
                    });
            // Room
            for (int i = 1; i <= 40; i++) {
                Rooms room = new Rooms("71" + (i < 10 ? "0" + i : i));
                roomsRepository.save(room);
            }
            Rooms room7133 = roomsRepository.findByRoomId("7133");

            // RoomBooking
            RoomBooking roomBooking1 = new RoomBooking(student1, room7133, bed1 ,"ต้องการเตียงเสริม");
            roomBookingRepository.save(roomBooking1);

            // inject necessary data for katalon ApproveBorrowedBikeTest
            BorrowedBike borrowedBike = new BorrowedBike(bikeType1, roomBooking1, dateType1);
            borrowedBikeRepository.save(borrowedBike);

            // ElectricType-LEO
            ElectricType electricType1 = new ElectricType("พัดลม");
            ElectricType electricType2 = new ElectricType("เต้ารีด");
            ElectricType electricType3 = new ElectricType("กะทะไฟฟ้า");
            ElectricType electricType4 = new ElectricType("คอม");
            Stream.of(electricType1, electricType2, electricType3, electricType4).forEach(electricType -> {
                electrictypeRepository.save(electricType);
            });

            // PackageType
            PackageType pt1 = new PackageType("ซองจดหมาย");
            PackageType pt2 = new PackageType("ซองเอกสาร");
            PackageType pt3 = new PackageType("ซองพลาสติก");
            PackageType pt4 = new PackageType("กล่องพัสดุ");
            Stream.of(pt1, pt2, pt3, pt4).forEach(pt -> {
                packageTypeRepository.save(pt);
                System.out.println(pt);
            });

            // STAFF
            Staff staff1 = new Staff("Chanwit Keawkasi", "chanwit", "62");
            Staff staff2 = new Staff("Nutthawut Sunthornrot", "black", "asdasd");
            Staff staff3 = new Staff("Apple Apyoon", "apyoon", "1234");

            Stream.of(staff1, staff2, staff3).forEach(staff -> {
                staffRepository.save(staff);
                System.out.println(staff);
            });

	     // PackageManagement
            PackageManagement pm1 = new PackageManagement(roomBooking1, pt4, staff3, "กล่องมีรอยเล็กน้อย");
            PackageManagement pm2 = new PackageManagement(roomBooking1, pt3, staff3, "สมบูรณ์");
            Stream.of(pm1, pm2).forEach(pm -> {
                packageManagementRepository.save(pm);
                System.out.println(pm);
            });

            Stream.of("รถจักรยานยนต์", "รถยนต์", "จักรยาน").forEach(type -> {
                VehicleType vehicleType = new VehicleType(); // สร้าง Object Customer
                vehicleType.setType(type); // set ชื่อ (name) ให้ Object ชื่อ Customer
                vehicleTypeRepository.save(vehicleType); // บันทึก Objcet ชื่อ Customer
            });

            // Repair
            DeviceType deviceType1 = new DeviceType("อุปกรณ์ไฟฟ้า");
            DeviceType deviceType2 = new DeviceType("อุปกรณ์ประปา");
            DeviceType deviceType3 = new DeviceType("คุรุภัณฑ์");
            DeviceType deviceType4 = new DeviceType("อื่นๆ");
            Stream.of(deviceType1, deviceType2, deviceType3, deviceType4).forEach(deviceType -> {
                deviceTypeRepository.save(deviceType); // บันทึก Objcet ชื่อ Customer
            });

            DeviceName deviceName1 = new DeviceName("หลอดไฟ", deviceType1);
            DeviceName deviceName2 = new DeviceName("โคมไฟ", deviceType1);
            DeviceName deviceName3 = new DeviceName("พัดลม", deviceType1);
            DeviceName deviceName4 = new DeviceName("สวิตซ์ เปิด-ปิดไฟ", deviceType1);
            DeviceName deviceName5 = new DeviceName("สายไฟ", deviceType1);

            DeviceName deviceName6 = new DeviceName("ท่อน้ำ", deviceType2);
            DeviceName deviceName7 = new DeviceName("ก็อกน้ำอ่างล่างหน้า", deviceType2);
            DeviceName deviceName8 = new DeviceName("ท่อน้ำทิ้ง", deviceType2);
            DeviceName deviceName9 = new DeviceName("ชักโครก", deviceType2);

            DeviceName deviceName10 = new DeviceName("มุ้งลวด", deviceType3);
            DeviceName deviceName11 = new DeviceName("เตียง", deviceType3);
            DeviceName deviceName12 = new DeviceName("เพดาน", deviceType3);
            DeviceName deviceName13 = new DeviceName("ลูกบิดประตู", deviceType3);
            DeviceName deviceName14 = new DeviceName("ประตูห้องพัก", deviceType3);
            DeviceName deviceName15 = new DeviceName("บานเกล็ด", deviceType3);

            DeviceName deviceName16 = new DeviceName("อื่นๆ (โปรดระบุอุปกรณ์ที่เสียในช่องระบุอาการ/ปัญหาที่เกิดขึ้น)",
                    deviceType4);
            Stream.of(deviceName1, deviceName2, deviceName3, deviceName4, deviceName5, deviceName6, deviceName7,
                    deviceName8, deviceName9, deviceName10, deviceName11, deviceName12, deviceName13, deviceName14,
                    deviceName15, deviceName16).forEach(deviceName -> {
                        deviceNameRepository.save(deviceName); // บันทึก Objcet ชื่อ Customer
                    });
        };
    }

    @Bean
    public FilterRegistrationBean simpleCorsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        // *** URL below needs to match the Vue client URL and port ***
        config.setAllowedOrigins(Collections.singletonList("http://localhost:8080"));
        config.setAllowedMethods(Collections.singletonList("*"));
        config.setAllowedHeaders(Collections.singletonList("*"));
        source.registerCorsConfiguration("/**", config);
        FilterRegistrationBean bean = new FilterRegistrationBean<>(new CorsFilter(source));
        bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
        return bean;
    }
}
