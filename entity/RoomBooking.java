package com.okta.springbootvue.entity;

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
@Table(name="ROOMBOOKING")
public class RoomBooking{

    @Id
    @SequenceGenerator(name="ROOMBOOKING_seq",sequenceName="ROOMBOOKING_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="ROOMBOOKING_seq")
    @Column(name = "ROOMBOOKING", unique = true, nullable = true)

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Student.class)
    @JoinColumn(name = "STUDENT", insertable = true)
    @JsonManagedReference
    private @NonNull Student student;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Room.class)
    @JoinColumn(name = "ROOM", insertable = true)
    @JsonManagedReference
    private @NonNull Room room;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Branch.class)
    @JoinColumn(name = "SEX_ID", insertable = true)
    @JsonManagedReference
    private @NonNull Branch branch;
}