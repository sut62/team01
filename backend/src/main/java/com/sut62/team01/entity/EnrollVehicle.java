package com.sut62.team01.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@Table(name = "ENROLL_VEHICLE")
public class EnrollVehicle {

    @Id
    @SequenceGenerator(name="enroll_vehicle_seq",sequenceName="enroll_vehicle_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="enroll_vehicle_seq")
    @Column(name = "ENROLL_VEHICLE_ID", unique = true, nullable = true)
    private Long id;
    
    @NotNull
    @Column(name = "ENROLL_DATE")
    private Date enrollDate;

    @NotNull
    @Size(max = 8)
    @Column(name = "LICENSE_PLATE")
    private String licensePlate;

    @NotNull
    @Size(max = 20)
    @Column(name = "BRAND_NAME")
    private String brandName;
    
    @NotNull
    @Size(max = 50)
    @Column(name = "OTHER_DETAILS")
    private String otherDetails;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER, targetEntity = VehicleType.class)
    @JoinColumn(name = "VEHICLE_TYPE_ID", insertable = true)
    @JsonManagedReference
    private VehicleType typeOfVehicle;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Staff.class)
    @JoinColumn(name = "STAFF_ID", insertable = true)
    @JsonManagedReference
    private Staff createdBy;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER, targetEntity = RoomBooking.class)
    @JoinColumn(name = "ROOMBOOKING_ID", insertable = true)
    @JsonManagedReference
    private RoomBooking enrolledStudents;
}