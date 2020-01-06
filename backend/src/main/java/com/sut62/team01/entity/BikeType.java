package com.din.dinentity.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import lombok.Data;

/**
 * BikeType
 */
@Entity
@Data

public class BikeType {

    @Id
    @Column(name = "BIKETYPE_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String type;

    // @OneToMany(mappedBy = "bikeType", cascade = CascadeType.ALL)
    // private Set<BorrowedBike> borrowedBikes;

}