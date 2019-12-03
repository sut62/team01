package com.sut62.team01.sut62team01.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;

/**
 * BorrowedBike
 */
@Entity
@Data
public class BorrowedBike {

    @Id
    @SequenceGenerator(name="borrowedBike_seq",sequenceName="borrowedBike_seq")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="borrowedBike_seq")
    @Column(name = "BORROWED_BIKE_ID")
    @NotNull private long id;
    
    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Room.class)
    @JoinColumn(name = "ROOM_ID", insertable = true)
    @JsonManagedReference
    private @NotNull Room room;

    //ใคร
    
    @ManyToOne(fetch = FetchType.EAGER, targetEntity = BorrowedBikeType.class)
    @JoinColumn(name = "BORROWED_BIKE_TYPE_ID", insertable = true)
    @JsonManagedReference
    private @NotNull BorrowedBikeType borrowedBikeType;

}