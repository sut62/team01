package com.sut62.team01.entity;

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
    private @NonNull Long id;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Students.class)
    @JoinColumn(name = "STUDENT_ID", insertable = true)
    @JsonManagedReference
    private @NonNull Students student;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Rooms.class)
    @JoinColumn(name = "ROOMS_ID", insertable = true)
    @JsonManagedReference
    private @NonNull Rooms rooms;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Branches.class)
    @JoinColumn(name = "BRANCHES_ID", insertable = true)
    @JsonManagedReference
    private @NonNull Branches branches;

    public RoomBooking() {
    }

    public RoomBooking(Students students, Rooms rooms, Branches branches) {
        this.student = students;
        this.rooms = rooms;
        this.branches = branches;

    }
}