package com.sut62.team01;

import java.util.Collections;
import java.util.stream.Stream;

import com.sut62.team01.entity.BikeType;
import com.sut62.team01.entity.Branches;
import com.sut62.team01.entity.DateType;
import com.sut62.team01.entity.RoomBooking;
import com.sut62.team01.entity.Rooms;
import com.sut62.team01.entity.Staff;
import com.sut62.team01.entity.Students;
import com.sut62.team01.entity.VehicleType;
import com.sut62.team01.repository.BikeTypeRepository;
import com.sut62.team01.repository.BorrowedBikeRepository;
import com.sut62.team01.repository.BranchesRepository;
import com.sut62.team01.repository.DateTypeRepository;
import com.sut62.team01.repository.RoomBookingRepository;
import com.sut62.team01.repository.RoomsRepository;
import com.sut62.team01.repository.StaffRepository;
import com.sut62.team01.repository.StudentsRepository;
import com.sut62.team01.repository.VehicleTypeRepository;

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
            StudentsRepository studentsRepository, BranchesRepository branchesRepository,
            RoomsRepository roomsRepository, StaffRepository staffRepository,
            VehicleTypeRepository vehicleTypeRepository) {
        return args -> {

            // TODO: Inject test data
            // BikeType
            BikeType bikeType1 = new BikeType("จักรยาน 2 ล้อ สำหรับคนเดียว");
            BikeType bikeType2 = new BikeType("จักรยาน 2 ล้อ สำหรับสองคน");
            BikeType bikeType3 = new BikeType("จักรยาน 3 ล้อ แบบมีหลังคา");
            BikeType bikeType4 = new BikeType("จักรยาน 3 ล้อ แบบไม่มีหลังคา");
            Stream.of(bikeType1, bikeType2, bikeType3, bikeType4).forEach(bikeType -> {
                bikeTypeRepository.save(bikeType);
                System.out.println(bikeType);
            });

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

            Branches branches1 = new Branches("สำนักวิชาวิทยาศาสตร์");
			Branches branches2 = new Branches("สำนักวิชาเทคโนโลยีสังคม");
			Branches branches3 = new Branches("สำนักวิชาเทคโนโลยีการเกษตร");
			Branches branches4 = new Branches("สำนักวิชาวิศวกรรมศาสตร์ ");
			Branches branches5 = new Branches("สำนักวิชาแพทยศาสตร์");
			Branches branches6 = new Branches("สำนักวิชาพยาบาลศาสตร์");
			Branches branches7 = new Branches("สำนักวิชาทันตแพทยศาสตร์");
			Branches branches8 = new Branches("สำนักวิชาสาธารณสุขศาสตร์");
			Stream.of(branches1,branches2,branches3,branches4,branches5,branches6,branches7,branches8).forEach(bra -> {
				branchesRepository.save(bra); 
			});
            // Room
            for (int i = 1; i <= 40; i++) {
                Rooms room = new Rooms("71" + (i < 10 ? "0" + i : i));
                roomsRepository.save(room);
            }
            Rooms room7133 = roomsRepository.findByRoomId("7133");

            // RoomBooking
            RoomBooking roomBooking1 = new RoomBooking(student1, room7133, branches1);
            roomBookingRepository.save(roomBooking1);

            // STAFF
            Staff staff1 = new Staff("Chanwit Keawkasi", "chanwit", "62");
            Staff staff2 = new Staff("Nutthawut Sunthornrot", "black", "asdasd");
            Staff staff3 = new Staff("Apple Apyoon", "apyoon", "1234");

            Stream.of(staff1, staff2, staff3).forEach(staff -> {
                staffRepository.save(staff);
                System.out.println(staff);
            });

            Stream.of("รถจักรยานยนต์", "รถยนต์", "จักรยาน").forEach(type -> {
				VehicleType	vehicleType = new VehicleType(); // สร้าง Object Customer
				vehicleType.setType(type); // set ชื่อ (name) ให้ Object ชื่อ Customer
				vehicleTypeRepository.save(vehicleType); // บันทึก Objcet ชื่อ Customer
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
