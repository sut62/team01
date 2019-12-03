package com.SUTDominatory.Entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;
import lombok.NonNull;

@Data
@Entity
@Table(name = "ElectricalRegistration")
public class ElectricalRegistration{
    @Id
    @SequenceGenerator(name = "ElectricalRegistration_seq", sequenceName = "ElectricalRegistration_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="ElectricalRegistration_seq")
    @Column(name = "ElectricalRegistration_ID", unique = true, nullable = true,insertable = true)    
    private @NonNull long id;

    private @NonNull Date ElecticalRegistrationdate;

    @OneToOne(fetch = FetchType.EAGER, targetEntity = RoomBooking.class)
    @JoinColumn(name = "ROOMBOOKING_ID", insertable = true)
    @JsonManagedReference
    private @NonNull RoomBooking roomBooking;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = ElecticalType.class)
    @JoinColumn(name = "ElecticalType_ID", insertable = true)
    @JsonManagedReference
    private @NonNull ElecticalType electicalType;

    @OneToOne(fetch = FetchType.EAGER, targetEntity = RoomNumber.class)
    @JoinColumn(name = "ROOMNUMBER_ID", insertable = true)
    @JsonManagedReference
    private @NonNull RoomNumber roomnumber;

    public ElectricalRegistration(){}
    public ElectricalRegistration(RoomBooking rb, ElecticalType et, RoomNumber rn){
        this.roomBooking = rb;
        this.ElecticalType = et;
        this.employee = rn;
        this.ElecticalRegistrationdate = new Date();
    }
}