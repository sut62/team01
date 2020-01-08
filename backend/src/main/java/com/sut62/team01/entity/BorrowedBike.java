package com.sut62.team01.entity;

import java.util.Date;

import javax.persistence.*;

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
    // @JsonIgnore
    private BikeType bikeType;

    // TODO: Change student_id to roomBooking_id
    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Students.class)
    @JoinColumn(name = "STUDENTS_ID", insertable = true)
    @JsonManagedReference
    private RoomBooking roomBooking;

    @ManyToOne
    @JoinColumn(name = "DATE_TYPE_ID")
    // @JsonIgnore
    private DateType dateType;

    private Date requestDate;
}