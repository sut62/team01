package com.Dormitory.dormitory.entity;

import lombok.*;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Data
@Entity
@Table(name = "REPAIR")

public class Repair{
    @Id
    @SequenceGenerator(name = "REPAIR_seq", sequenceName = "REPAIR_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "REPAIR_seq")
    @Column(name = "SREPAIR_ID", unique = true, nullable = true)

    private Long id;

    @Column(name = "MANNER")
    private String manner;

    @Column(name ="Repair_Date")
    private Date repairDate;
    
    @ManyToOne(fetch = FetchType.EAGER, targetEntity = DeviceType.class)
    @JoinColumn(name = "DEVICETYPE_ID", insertable = true)
    private DeviceType type;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Room.class)
    @JoinColumn(name = "ROOM_ID", insertable = true)
    private Room room;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Student.class)
    @JoinColumn(name = "STUDENT_ID", insertable = true)
    private Student studentNotify;


}