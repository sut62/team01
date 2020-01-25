package com.sut62.team01.entity;

import java.util.Date;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;

@Data
@Entity
@Table(name = "packageManagement")
public class PackageManagement {
    @Id
    @SequenceGenerator(name = "packageManagement_seq", sequenceName = "packageManagement_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "packageManagement_seq")
    @Column(name = "PACKAGEMANAMENT_ID", unique = true, nullable = true, insertable = true)
    private @NotNull long id;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = RoomBooking.class)
    @JoinColumn(name = "ROOMBOOKING_ID", insertable = true)
    @JsonManagedReference
    private @NotNull RoomBooking roomBooking;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Staff.class)
    @JoinColumn(name = "STAFF_ID", insertable = true)
    @JsonManagedReference
    private @NotNull Staff staff;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = PackageType.class)
    @JoinColumn(name = "PACKAGETYPE_ID", insertable = true)
    @JsonManagedReference
    private @NotNull PackageType packageType;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+7")
    @Column(name = "packageDATE")
    private @NotNull Date packageDate;

    @Size(min = 5, max = 20)
    private String details;

    public PackageManagement() {
    }

    public PackageManagement(RoomBooking roomBooking, PackageType packageType, Staff staff, String details) {
        this.roomBooking = roomBooking;
        this.packageType = packageType;
        this.staff = staff;
        this.details = details;
        this.packageDate = new Date();
    }
}