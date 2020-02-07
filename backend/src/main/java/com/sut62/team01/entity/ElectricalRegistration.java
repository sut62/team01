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
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;

@Data
@Entity
@Table(name = "ElectricalRegistration")
public class ElectricalRegistration {
    @Id
    @SequenceGenerator(name = "ElectricalRegistration_seq", sequenceName = "ElectricalRegistration_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ElectricalRegistration_seq")
    @Column(name = "ElectricalRegistration_ID", unique = true, nullable = true, insertable = true)
    private @NotNull long id;

    // TODO: แก้ชื่อตัวแปรให้ขึ้นต้นด้วยตัวพิมพ์เล็ก
    private @NotNull @PastOrPresent Date electricalRegistrationDate;

    @OneToOne(fetch = FetchType.EAGER, targetEntity = RoomBooking.class)
    @JoinColumn(name = "ROOMBOOKING_ID", insertable = true)
    @JsonManagedReference
    private @NotNull RoomBooking roomBooking;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = ElectricType.class)
    @JoinColumn(name = "ELECTRICTYPE_ID", insertable = true)
    @JsonManagedReference
    private @NotNull ElectricType electricType;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Staff.class)
    @JoinColumn(name = "STAFF_ID", insertable = true)
    @JsonManagedReference
    private @NotNull Staff staff;


    private @Size(max = 30) String details;

    public ElectricalRegistration() {
    }

    public ElectricalRegistration(RoomBooking rb, ElectricType et, Staff st, String details) {
        this.roomBooking = rb;
        this.electricType = et;
        this.staff = st;
        this.details = details;
        this.electricalRegistrationDate = new Date();
    }
}