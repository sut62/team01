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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@Entity
@NoArgsConstructor
@Table(name = "REPAIR")

public class Repair {
    @Id
    @SequenceGenerator(name = "REPAIR_seq", sequenceName = "REPAIR_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "REPAIR_seq")
    @Column(name = "SREPAIR_ID", unique = true, nullable = true)

    private  Long id;

    @NotNull
    @Size(min = 3,max = 30) 
    @Column(name = "List")
    private  String  list;

    @NotNull
    @Pattern(regexp = "\\d{10}")
    @Column(name = "Phone")
    private String tel;


    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+7")
    @Column(name = "Repair_Date")
    private    @NotNull Date repairDate;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = DeviceType.class)
    @JoinColumn(name = "DEVICETYPE_ID", insertable = true)
    @JsonManagedReference
    @NotNull
    private DeviceType type;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = DeviceName.class)
    @JoinColumn(name = "DEVICENAMES_ID", insertable = true)
    @JsonManagedReference
    @NotNull
    private DeviceName name;
    
    @ManyToOne(fetch = FetchType.EAGER , targetEntity = RoomBooking.class)
    @JoinColumn(name = "ROOMBOOKING_ID", insertable = true)
    @JsonManagedReference
    @NotNull
    private RoomBooking enrolled;

  

   

}