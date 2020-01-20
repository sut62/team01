package com.sut62.team01.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Data
public class Bike {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "BIKETYPE_ID")
    @JsonManagedReference
    @NotNull
    private BikeType bikeType;

    @NotNull
    private String bikeId;

    @NotNull
    private boolean available;
}
