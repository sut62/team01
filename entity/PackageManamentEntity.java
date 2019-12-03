package com.app.app.entity;

import java.util.Date;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.NonNull;

@Data
@Entity
@Table(name = "PackageManagement")
public class PackageManagement{
    @Id
    @SequenceGenerator(name="PackageManagement_seq",sequenceName="PackageManagement_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="PackageManagement_seq")
    @Column(name = "PACKAGEMANAMENT_ID", unique = true, nullable = true,insertable = true)
    private long id;

    @Column(name="DATE")
    private @NonNull Date Date;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Room.class)
    @JoinColumn(name = "ROOM_ID", insertable = true)
    @JsonManagedReference
    private @NonNull Room room; 

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = PackageType.class)
    @JoinColumn(name = "PACKAGETYPE_ID", insertable = true)
    @JsonManagedReference
    private @NonNull PackageType packageType;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = RoomBooking.class)
    @JoinColumn(name = "ROOMBOOKING_ID", insertable = true)
    @JsonManagedReference
    private @NonNull RoomBooking roomBooking;
    

    public PackageManagement(){}
    public PackageManagement(Room room, PackageType packageType,  RoomBooking roomBooking){
        this.Date = new Date();
        this.room = room;
        this.packageType = packageType;
        this.roomBooking = roomBooking;
        
    }
}