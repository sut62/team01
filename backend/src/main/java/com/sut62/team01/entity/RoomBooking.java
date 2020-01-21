package com.sut62.team01.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.*;

@Data
@Entity
@Table(name = "ROOMBOOKING")
public class RoomBooking {

    @Id
    @SequenceGenerator(name = "ROOMBOOKING_seq", sequenceName = "ROOMBOOKING_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ROOMBOOKING_seq")
    @Column(name = "ROOMBOOKING_ID", unique = true, nullable = true)
    private Long id;

    @OneToOne(fetch = FetchType.EAGER, targetEntity = Students.class)
    @JoinColumn(name = "STUDENT_ID", insertable = true)
    @JsonManagedReference
    private @NotNull Students student;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Rooms.class)
    @JoinColumn(name = "ROOMS_ID", insertable = true)
    @JsonManagedReference
    private @NotNull Rooms rooms;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Bed.class)
    @JoinColumn(name = "BED_ID", insertable = true)
    @JsonManagedReference
    private @NotNull Bed bed;

    @NotNull
    @Size(min = 5 , max = 30)
    @Column(name = "DETAIL")
    private String detail;

    public RoomBooking() {
    }

    public RoomBooking(Students students, Rooms rooms, Bed bed, String detail) {
        this.student = students;
        this.rooms = rooms;
        this.bed = bed;
        this.detail=detail;
    }
}