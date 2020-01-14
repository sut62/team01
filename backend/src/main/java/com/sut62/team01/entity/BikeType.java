package com.sut62.team01.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

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

    @NotNull
    private String type;

    public BikeType() {
    }

    public BikeType(String type) {
        this.type = type;
    }

}