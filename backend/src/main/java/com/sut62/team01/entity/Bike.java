package com.sut62.team01.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Data
public class Bike {

    @Id
    @Column(name = "BIKE_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "BIKETYPE_ID")
    @JsonManagedReference
    @NotNull
    private BikeType bikeType;

    @NotNull
    @Pattern(regexp = "Bike+\\d{4}")
    private String name;

    @NotNull
    private boolean available = true;

    public Bike(){}

    public Bike(@NotNull BikeType bikeType, @NotNull String name) {
        this.bikeType = bikeType;
        this.name = name;
    }
}
