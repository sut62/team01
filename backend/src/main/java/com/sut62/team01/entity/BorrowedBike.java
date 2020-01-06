package com.sut62.team01.entity;

import java.util.Date;

import javax.persistence.*;

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

    @ManyToOne
    @JoinColumn(name = "STUDENT_ID")
    // @JsonIgnore
    private Student student;

    private Date borrowedDate;
}