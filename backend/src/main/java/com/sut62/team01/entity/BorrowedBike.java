package com.sut62.team01.entity;

import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;

/**
 * BorrowedBike
 */
@Entity
@Data
@Table(name = "borrowedbike")
public class BorrowedBike {

    @Id
    @Column(name = "BORROWEDBIKE_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "BIKETYPE_ID")
    @JsonManagedReference
    @NotNull
    private BikeType bikeType;

    // TODO: Change student_id to roomBooking_id
    @ManyToOne(fetch = FetchType.EAGER, targetEntity = RoomBooking.class)
    @JoinColumn(name = "ROOMBOOKING_ID")
    @JsonManagedReference
    @NotNull
    private RoomBooking roomBooking;

    @ManyToOne
    @JoinColumn(name = "DATE_TYPE_ID")
    @JsonManagedReference
    @NotNull
    private DateType dateType;

    //    Null ได้ เพราะรอ staff ให้ยืม
    @ManyToOne
    @JoinColumn(name = "STAFF_ID")
    @JsonManagedReference
    private Staff staff;

    @NotNull
    private Date requestDate;

//    Null ได้ เพราะรอ staff ให้ยืม
    @ManyToOne
    @JoinColumn(name = "BIKE_ID")
    @JsonManagedReference
    private Bike bike;

    public BorrowedBike() {
    }

    public BorrowedBike(@NotNull BikeType bikeType, @NotNull RoomBooking roomBooking, @NotNull DateType dateType) {
        this.bikeType = bikeType;
        this.roomBooking = roomBooking;
        this.dateType = dateType;
        this.requestDate = new Date();
    }
}