package com.cpe.dormsys.Entity;

import java.sql.Date;

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
@Table(name="ENROLL_VEHICLE")
public class EnrollVehicle {

    @Id
    @SequenceGenerator(name="enroll_vehicle_seq",sequenceName="enroll_vehicle_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="enroll_vehicle_seq")
    @Column(name = "ENROLL_VEHICLE_ID", unique = true, nullable = true)
    private @NonNull Long id;

    @Column(name="ENROLL_DATE")
    private @NonNull Date enrollDate;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = RoomBooking.class)
    @JoinColumn(name = "ROOMBOOKING_ID", insertable = true)
    @JsonManagedReference
    private @NonNull RoomBooking roomBooking;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Room.class)
    @JoinColumn(name = "ROOM_ID", insertable = true)
    @JsonManagedReference
    private @NonNull Room room;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = VehicleType.class)
    @JoinColumn(name = "VEHICLE_TYPR_ID", insertable = true)
    @JsonManagedReference
    private @NonNull VehicleType vehicleType;
}
